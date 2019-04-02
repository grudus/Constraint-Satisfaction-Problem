package pwr.csp.printer

import pwr.csp.commons.Board

class FutushikiBoardPrinter: BoardPrinter<Int> {

    override fun printBoard(board: Board<Int>) {
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
    }
}
