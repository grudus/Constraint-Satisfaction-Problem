package pwr.csp.games

import pwr.csp.commons.Board
import pwr.csp.commons.BoardPoint
import pwr.csp.printer.BoardPrinter

class Futushiki(private var board: Board<Int>,
                private val relations: List<GreaterThanRelation>,
                private val boardPrinter: BoardPrinter<Int>) {

    fun updateBoard(point: BoardPoint, value: Int) {
        board += (point to value)
    }

    fun isCompleted(): Boolean =
            board.all { (_, value) -> value > 0 } && isBoardValid()

    fun isBoardValid(): Boolean =
            relationsAreValid() && boardHasUniqueValues()


    fun printBoard() {
        boardPrinter.printBoard(board)
        println("\nRelacje: ")
        relations.forEach { (greater, smaller) ->
            println("$greater > $smaller")
        }
    }

    private fun boardHasUniqueValues(): Boolean =
            board.all { (point, value) ->
                findPeers(point).none { board.getValue(it) == value }
            }

    private fun relationsAreValid(): Boolean =
            relations.all { (greater, smaller) ->
                board.getValue(greater) > board.getValue(smaller)
            }


    private fun findPeers(boardPoint: BoardPoint): List<BoardPoint> =
            board.keys.filter { it.row == boardPoint.row || it.col == boardPoint.col }
}
