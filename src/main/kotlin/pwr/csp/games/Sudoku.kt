package pwr.csp.games

import pwr.csp.commons.Board
import pwr.csp.commons.BoardPoint

class Sudoku(private var board: Board<List<Int>>) {

    fun isBoardValid(): Boolean {
        if (board.any { (_, possibleValues) -> possibleValues.isEmpty() })
            return false

        return board.filter { (_, possibleValues) -> possibleValues.size == 1 }
                .mapValues { it.value[0] }
                .all { (boardPoint, value) ->
                    val peers = findPeers(boardPoint)

                    return peers.none { board.getValue(it)[0] == value }
                }
    }

    fun isCompleted(): Boolean =
            board.all { (_, possibleValues) -> possibleValues.size == 1 }
                    && isBoardValid()

    fun updateBoard(point: BoardPoint, value: Int) {
        board += (point to listOf(value))
    }

    fun printBoard() {
        val list = board.toList()

        list.sortedBy { it.first }
                .forEachIndexed { index, (boardPoint, score) ->
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


    private fun findPeers(boardPoint: BoardPoint): List<BoardPoint> {
        val rowColPeers = board.keys.filter { it.row == boardPoint.row || it.col == boardPoint.col }
        val square = squares.find { it.contains(boardPoint) }!!

        return (rowColPeers + square).distinct() - boardPoint
    }

    companion object {
        private val rowSquares = listOf(listOf('A', 'B', 'C'), listOf('D', 'E', 'F'), listOf('G', 'H', 'I'))
        private val colSquares = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9))

        private val squares: List<List<BoardPoint>> = rowSquares.flatMap { rows ->
            colSquares.map { cols ->
                rows.flatMap { row -> cols.map { col -> BoardPoint(row, col) } }
            }
        }
    }
}
