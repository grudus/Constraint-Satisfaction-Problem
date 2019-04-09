package pwr.csp

import pwr.csp.algorithm.ForwardCheckingSolver
import pwr.csp.algorithm.Solver
import pwr.csp.algorithm.heuristic.MostConstrainedValueSelector
import pwr.csp.algorithm.heuristic.ValueSelector
import pwr.csp.games.Game
import pwr.csp.reader.FutushikiReader
import pwr.csp.reader.GameReader
import pwr.csp.reader.SudokuReader
import java.io.File
import java.util.*
import java.util.Arrays.toString

private enum class GameStrategy(val reader: GameReader<out Game>) {
    SUDOKU(SudokuReader()),
    FUTOSHIKI(FutushikiReader())
}

private enum class SolverStrategy(val solver: Solver) {
    FORWARD_CHECKING(ForwardCheckingSolver())
}

private enum class ValueSelectorStrategy(val valueSelector: ValueSelector) {
    MOST_CONSTRAINED(MostConstrainedValueSelector())
}

fun main(args: Array<String>) {
    if (args.size < 4) {
        println("Program expects 4 arguments: ")
        println("1) Reader: One of ${toString(GameStrategy.values())}")
        println("2) Solver: One of ${toString(SolverStrategy.values())}")
        println("3) Value selector: One of ${toString(ValueSelectorStrategy.values())}")
        println("4) File with data to solve")
        return
    }

    println("__ Hello in the Constraint Satisfaction Problem __")

    val reader = GameStrategy.valueOf(args[0]).reader
    val solver = SolverStrategy.valueOf(args[1]).solver
    val valueSelector = ValueSelectorStrategy.valueOf(args[2]).valueSelector
    val file = File(args[3])

    val game: Game = reader.read(file)
    game.printBoard()

    println("\n")

    val solutions: List<Game> = GameSolutionsFinder.findSolutions(
            game,
            solver,
            valueSelector
    )

    println("Found ${solutions.size} solutions:\n")
    solutions.forEach {it.printBoard()}
}
