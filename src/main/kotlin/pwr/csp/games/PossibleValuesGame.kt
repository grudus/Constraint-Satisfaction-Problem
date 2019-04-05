package pwr.csp.games

import pwr.csp.commons.Board
import pwr.csp.commons.BoardPoint
import pwr.csp.printer.BoardPrinter

abstract class PossibleValuesGame(
        private val board: Board<List<Int>>,
        private val boardPrinter: BoardPrinter<List<Int>>
) : Game {

    abstract fun areConstraintsMet(): Boolean

    override fun isBoardValid(): Boolean {
        if (board.any { (_, possibleValues) -> possibleValues.isEmpty() })
            return false

        return areConstraintsMet()
    }

    override fun getBoardWithPossibleValues(): Board<List<Int>> = board

    override fun printBoard() {
        boardPrinter.printBoard(board)
    }

    override fun isCompleted(): Boolean =
            board.all { (_, possibleValues) -> possibleValues.size == 1 }
                    && isBoardValid()


    override fun findPossibleValues(boardPoint: BoardPoint): List<Int> =
            board.getValue(boardPoint).toList()


}
