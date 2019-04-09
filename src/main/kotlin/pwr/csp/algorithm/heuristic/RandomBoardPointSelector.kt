package pwr.csp.algorithm.heuristic

import pwr.csp.commons.BoardPoint
import pwr.csp.games.Game
import kotlin.random.Random

class RandomBoardPointSelector: BoardPointSelector {
    override fun select(game: Game): BoardPoint {
        val points = game.getBoardWithPossibleValues()
                .filter { (_, values) -> values.size > 1 }
                .keys
                .toList()

        return points[Random.nextInt(points.size)]
    }

}
