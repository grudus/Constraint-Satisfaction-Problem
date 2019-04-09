package pwr.csp.algorithm.heuristic

import pwr.csp.commons.BoardPoint
import pwr.csp.games.Game

class MostLimitingBoardPointSelector: BoardPointSelector {

    override fun select(game: Game): BoardPoint {
        val points = game.getBoardWithPossibleValues()
                .filter { (_, values) -> values.size > 1 }
                .keys
                .toList()

        return points
                .map { point -> Pair(point, game.findPeers(point).map(game::findPossibleValues)) }
                .maxBy { it.second.size }
                ?.first
                ?: throw RuntimeException("Cannot find maximal " + game.getBoardWithPossibleValues())

    }
}
