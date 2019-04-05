package pwr.csp

import pwr.csp.algorithm.ForwardCheckingSolver
import pwr.csp.algorithm.heuristic.MostConstrainedValueSelector
import pwr.csp.reader.SudokuReader
import java.io.File

fun main() {
    println("__ Hello in the Constraint Satisfaction Problem __")
    val sudokuFile = File("res/test_data/sudoku/test_4")

    val sudoku = SudokuReader().read(sudokuFile)
    sudoku.printBoard()

    println("\n")

    GameSolutionsFinder.findSolutions(
            sudoku,
            ForwardCheckingSolver(),
            MostConstrainedValueSelector()
    ).forEach { it.printBoard() }
}
