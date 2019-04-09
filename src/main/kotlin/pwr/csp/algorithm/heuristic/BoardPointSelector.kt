package pwr.csp.algorithm.heuristic

import pwr.csp.commons.BoardPoint
import pwr.csp.games.Game

interface BoardPointSelector {
    fun select(game: Game): BoardPoint
}
