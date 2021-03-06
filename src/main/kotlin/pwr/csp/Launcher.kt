package pwr.csp

import pwr.csp.algorithm.ForwardCheckingSolver
import pwr.csp.algorithm.SolutionDescription
import pwr.csp.algorithm.Solver
import pwr.csp.algorithm.BacktrackingSolver
import pwr.csp.algorithm.heuristic.MostConstrainedBoardPointSelector
import pwr.csp.algorithm.heuristic.BoardPointSelector
import pwr.csp.algorithm.heuristic.MostLimitingBoardPointSelector
import pwr.csp.algorithm.heuristic.RandomBoardPointSelector
import pwr.csp.games.Futoshiki
import pwr.csp.games.Game
import pwr.csp.games.PossibleValuesGame
import pwr.csp.reader.FutoshikiReader
import pwr.csp.reader.GameReader
import pwr.csp.reader.SudokuReader
import java.io.File
import java.lang.IllegalArgumentException
import java.util.Arrays.toString
import java.util.concurrent.Executors
import java.util.concurrent.ExecutorService
import java.util.concurrent.atomic.AtomicInteger


private enum class SolverStrategy(val solver: Solver) {
    FORWARD_CHECKING(ForwardCheckingSolver()),
    BACKTRACKING(BacktrackingSolver()),
}

private enum class ValueSelectorStrategy(val boardPointSelector: BoardPointSelector) {
    MOST_CONSTRAINED(MostConstrainedBoardPointSelector()),
    MOST_LIMITING(MostLimitingBoardPointSelector()),
}


fun main(args: Array<String>) {
    if (args.size < 3) {
        throw IllegalArgumentException("Specify input file with puzzle to solve and SolverStrategy and ValueSelectorStrategy")
    }

    println("__ Hello in the Constraint Satisfaction Problem __")

    val reader: GameReader<out Game> =
            if (args[0].contains("udoku"))
                SudokuReader()
            else FutoshikiReader()

    val file = File(args[0])
    val game: Game = reader.read(file)

    val solver = SolverStrategy.valueOf(args[1])
    val selector = ValueSelectorStrategy.valueOf(args[2])

    val searchAll = try {
        args[3].toBoolean()
    } catch (e: Exception) {
        true
    }

    println("Results for file: $file")
    println("Using solver strategy $solver")
    println("Using value selector strategy $selector")
    println("Searching all: $searchAll")
    println("\n")
    game.printBoard()
    println("\n\n")


    val solutionDescription: SolutionDescription = GameSolutionsFinder.findSolutions(
            game,
            solver.solver,
            selector.boardPointSelector,
            searchAll
    )

    println(solutionDescription)

    solutionDescription.solutions().forEach {
        it.printBoard()
    }

}
