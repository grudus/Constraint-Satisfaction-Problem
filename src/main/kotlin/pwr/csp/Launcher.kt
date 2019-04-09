package pwr.csp

import pwr.csp.algorithm.ForwardCheckingSolver
import pwr.csp.algorithm.SolutionDescription
import pwr.csp.algorithm.Solver
import pwr.csp.algorithm.BacktrackingSolver
import pwr.csp.algorithm.heuristic.MostConstrainedBoardPointSelector
import pwr.csp.algorithm.heuristic.BoardPointSelector
import pwr.csp.algorithm.heuristic.MostLimitingBoardPointSelector
import pwr.csp.algorithm.heuristic.RandomBoardPointSelector
import pwr.csp.games.Game
import pwr.csp.reader.FutoshikiReader
import pwr.csp.reader.GameReader
import pwr.csp.reader.SudokuReader
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

private enum class SolverStrategy(val solver: Solver) {
    FORWARD_CHECKING(ForwardCheckingSolver()),
    BACKTRACKING(BacktrackingSolver()),
}

private enum class ValueSelectorStrategy(val boardPointSelector: BoardPointSelector) {
    MOST_CONSTRAINED(MostConstrainedBoardPointSelector()),
    MOST_LIMITING(MostLimitingBoardPointSelector()),
    RANDOM(RandomBoardPointSelector()),
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Specify input file with puzzle to solve")
        return
    }

    val numberOfTests = SolverStrategy.values().size + ValueSelectorStrategy.values().size

    val executor = Executors.newFixedThreadPool(numberOfTests)
    val counter = AtomicInteger(1)

    println("__ Hello in the Constraint Satisfaction Problem __")

    val reader: GameReader<out Game> =
            if (args[0].contains("udoku"))
                SudokuReader()
            else FutoshikiReader()

    val file = File(args[0])
    val game: Game = reader.read(file)

    println("Results for file: $file")

    SolverStrategy.values().forEach { solver ->
        ValueSelectorStrategy.values().forEach { selector ->

            executor.execute {
                val solutionDescription: SolutionDescription = GameSolutionsFinder.findSolutions(
                        game,
                        solver.solver,
                        selector.boardPointSelector
                )

                println("Solver:  ${solver.name}")
                println("Value selector: ${selector.name}")
                println()
                println(solutionDescription)
                if (counter.incrementAndGet() == 6)
                    executor.shutdown()
            }
        }
    }
}
