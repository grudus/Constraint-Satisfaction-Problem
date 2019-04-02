package pwr.csp.printer

import pwr.csp.commons.Board

class SudokuBoardPrinter : BoardPrinter<List<Int>> {

    override fun printBoard(board: Board<List<Int>>) {
        val list = board.toList()

        list.sortedBy { it.first }
                .forEach { (boardPoint, score) ->
                    val point = if (score.size == 1) score[0].toString() else "0"
                    print("$point ")
                    if (boardPoint.col == 9)
                        println()
                    else if (boardPoint.col % 3 == 0)
                        print("| ")

                    if (boardPoint.row in listOf('C', 'F') && boardPoint.col == 9)
                        println("------+-------+------")
                }
    }
}
