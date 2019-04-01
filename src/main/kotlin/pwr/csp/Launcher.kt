package pwr.csp

import pwr.csp.commons.BoardPoint
import pwr.csp.io.FutushikiReader
import java.io.File

fun main() {
    println("__ Hello in the Constraint Satisfaction Problem __")
    val futushikiFile = File("res/start_data/futoshiki/test_futo_4_0.txt")

    val futushiki = FutushikiReader().read(futushikiFile)

    futushiki.updateBoard(BoardPoint("A3"), 4)
    futushiki.updateBoard(BoardPoint("A3"), 7)

    futushiki.printBoard()

    println(futushiki.isBoardValid())
}
