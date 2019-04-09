package pwr.csp.games

import pwr.csp.commons.Board
import pwr.csp.commons.BoardPoint
import pwr.csp.printer.BoardPrinter

data class Sudoku(private val board: Board<List<Int>>,
                  private val boardPrinter: BoardPrinter<List<Int>>) : PossibleValuesGame(board, boardPrinter) {

    override fun areConstraintsMet(): Boolean =
            board.filter { (_, possibleValues) -> possibleValues.size == 1 }
                    .mapValues { it.value[0] }
                    .all { (boardPoint, value) ->
                        val peers = findPeers(boardPoint)

                        return peers.none { board.getValue(it)[0] == value }
                    }


    override fun update(boardPoint: BoardPoint, elem: Int): Game =
            copy(board = board + (boardPoint to listOf(elem)))

    override fun eliminateInconsistentValues(): Game {
        var eliminated: Boolean
        val mutableBoard = HashMap(board).toMutableMap()

        do {
            eliminated = false

            mutableBoard.forEach { point, values ->
                if (values.size == 1) {
                    val value = values[0]
                    val peers = findPeers(point)

                    peers.forEach { peer ->
                        if (mutableBoard[peer]!!.contains(value)) {
                            eliminated = true
                            mutableBoard[peer] = mutableBoard[peer]!! - value
                        }
                    }
                }
            }

        } while (eliminated)

        return this.copy(board = mutableBoard)
    }

    override fun findPeers(boardPoint: BoardPoint): List<BoardPoint> {
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
