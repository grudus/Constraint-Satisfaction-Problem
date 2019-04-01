package pwr.csp.games

import pwr.csp.commons.Board
import pwr.csp.commons.BoardPoint

class Futushiki(private var board: Board<Int>, private val relations: List<GreaterThanRelation>) {

    fun updateBoard(point: BoardPoint, value: Int) {
        board += (point to value)
    }

    fun isCompleted(): Boolean =
            board.all {(_, value) -> value > 0} && isBoardValid()

    fun isBoardValid(): Boolean =
            relations.all { (greater, smaller) ->
                board.getValue(greater) > board.getValue(smaller)
            }


    fun printBoard() {
        val list = board.toList()
        val cols = list.last().first.col

        print("  | ")
        (1..cols).forEach { print("$it ") }
        println()
        (0 until cols).forEach { print("---") }
        println()

        list.sortedBy { it.first }
                .forEachIndexed { index, (boardPoint, score) ->
                    if (index % cols == 0)
                        print("${boardPoint.row} | ")
                    print("$score ")
                    if (boardPoint.col == cols)
                        println()
                }

        println("\nRelacje: ")
        relations.forEach { (greater, smaller) ->
            println("$greater > $smaller")
        }
    }
}
