package pwr.csp.games

import pwr.csp.commons.Board
import pwr.csp.commons.BoardPoint
import pwr.csp.printer.BoardPrinter

data class Futushiki(private var board: Board<List<Int>>,
                     private val relations: List<GreaterThanRelation>,
                     private val boardPrinter: BoardPrinter<List<Int>>) : PossibleValuesGame(board, boardPrinter) {

    override fun update(boardPoint: BoardPoint, elem: Int): Game =
            copy(board = board + (boardPoint to listOf(elem)))

    override fun areConstraintsMet(): Boolean =
            relationsAreValid() && boardHasUniqueValues()

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

        } while (!eliminated)

        return this.copy(board = mutableBoard)
    }

    override fun printBoard() {
        boardPrinter.printBoard(board)
        println("\nRelacje: ")
        relations.forEach { (greater, smaller) ->
            println("$greater > $smaller")
        }
    }

    private fun boardHasUniqueValues(): Boolean =
            board
                    .filterValues { it.size == 1 }
                    .mapValues { it.value[0] }
                    .all { (point, value) ->
                        findPeers(point)
                                .map { board.getValue(it) }
                                .filter { it.size == 1 }
                                .map { it[0] }
                                .none { peerValue -> peerValue == value }
                    }

    private fun relationsAreValid(): Boolean =
            relations.all { (greater, smaller) ->
                if (board.getValue(greater).size == 1 || board.getValue(smaller).size == 1)
                    board.getValue(greater)[0] > board.getValue(smaller)[0]
                else true
            }


    private fun findPeers(boardPoint: BoardPoint): List<BoardPoint> =
            board.keys.filter { it.row == boardPoint.row || it.col == boardPoint.col } - boardPoint
}
