package pwr.csp

import pwr.csp.commons.BoardPoint
import pwr.csp.io.FutushikiReader
import java.io.File

fun main() {
    println("__ Hello in the Constraint Satisfaction Problem __")
    val futushikiFile = File("res/start_data/futoshiki/test_futo_4_0.txt")

    val read = FutushikiReader().read(futushikiFile)

    read.updateBoard(BoardPoint("A3"), 4)
    read.updateBoard(BoardPoint("A3"), 7)

    read.printBoard()
}
