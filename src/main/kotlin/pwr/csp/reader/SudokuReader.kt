package pwr.csp.reader

import pwr.csp.commons.Board
import pwr.csp.commons.BoardPoint
import pwr.csp.games.Sudoku
import pwr.csp.printer.SudokuBoardPrinter
import java.io.File

class SudokuReader : GameReader<Sudoku> {

    override fun read(file: File): Sudoku {
        val sudokuBoard = file.readLines()[0].split(",")[0]
                .map { Character.getNumericValue(it) }

        val boardSize = 9

        val board: Board<List<Int>> = sudokuBoard.mapIndexed { index, number ->
            val row = 'A' + (index / boardSize)
            val col = index % boardSize + 1
            val possibleValues = if (number == 0) (1..9).toList() else listOf(number)
            Pair(BoardPoint(row, col), possibleValues)
        }
                .groupBy({ (point, _) -> point}, { (_, value) -> value })
                .mapValues { it.value[0] }

        return Sudoku(board, SudokuBoardPrinter())
    }
}
