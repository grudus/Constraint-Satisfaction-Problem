package pwr.csp.algorithm.heuristic

import pwr.csp.commons.BoardPoint
import pwr.csp.games.Game

interface ValueSelector {
    fun selectValue(game: Game): BoardPoint
}
