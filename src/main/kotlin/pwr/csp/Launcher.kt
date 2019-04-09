package pwr.csp

import pwr.csp.algorithm.ForwardCheckingSolver
import pwr.csp.algorithm.heuristic.MostConstrainedValueSelector
import pwr.csp.reader.FutushikiReader
import pwr.csp.reader.SudokuReader
import java.io.File

fun main() {
    println("__ Hello in the Constraint Satisfaction Problem __")
    val sudokuFile = File("res/test_data/sudoku/test_2")

    val sudoku = SudokuReader().read(sudokuFile)
    sudoku.printBoard()

    println("\n")

    GameSolutionsFinder.findSolutions(
            sudoku,
            ForwardCheckingSolver(),
            MostConstrainedValueSelector()
    ).forEach { it.printBoard() }

    val futushikiFile = File("res/research_data/futoshiki/test_futo_4_0.txt")

    val futoshiki = FutushikiReader().read(futushikiFile)
    futoshiki.printBoard()


    GameSolutionsFinder.findSolutions(
            futoshiki,
            ForwardCheckingSolver(),
            MostConstrainedValueSelector()
    ).forEach { it.printBoard() }
}
