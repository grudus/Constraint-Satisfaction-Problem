package pwr.csp.games

import pwr.csp.commons.Board
import pwr.csp.commons.BoardPoint

interface Game {
    fun isBoardValid(): Boolean
    fun isCompleted(): Boolean
    fun update(boardPoint: BoardPoint, elem: Int): Game
    fun printBoard()
    fun findPossibleValues(boardPoint: BoardPoint): List<Int>
    fun eliminateInconsistentValues(): Game
    fun getBoardWithPossibleValues(): Board<List<Int>>
    fun findPeers(boardPoint: BoardPoint): List<BoardPoint>
}
