package pwr.csp.printer

import pwr.csp.commons.Board

class FutoshikiBoardPrinter: BoardPrinter<List<Int>> {

    override fun printBoard(board: Board<List<Int>>) {
        val list = board.toList()
        val cols = Math.sqrt(list.size.toDouble()).toInt()

        print("  | ")
        (1..cols).forEach { print("$it ") }
        println()
        (0 until cols).forEach { print("---") }
        println()

        list.sortedBy { it.first }
                .forEachIndexed { index, (boardPoint, possibleValues) ->
                    val value = if (possibleValues.size == 1) possibleValues[0].toString() else "0"

                    if (index % cols == 0)
                        print("${boardPoint.row} | ")
                    print("$value ")
                    if (boardPoint.col == cols)
                        println()
                }
    }
}
