package pwr.csp.printer

import pwr.csp.commons.Board

interface BoardPrinter<T> {
    fun printBoard(board: Board<T>)
}
