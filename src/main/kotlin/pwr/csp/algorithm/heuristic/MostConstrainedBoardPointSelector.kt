package pwr.csp.algorithm.heuristic

import pwr.csp.commons.BoardPoint
import pwr.csp.games.Game

class MostConstrainedBoardPointSelector : BoardPointSelector {

    override fun select(game: Game): BoardPoint =
            game.getBoardWithPossibleValues().entries
                    .filter { (_, values) -> values.size > 1 }
                    .minBy { (_, values) -> values.size }
                    ?.key
                    ?: throw RuntimeException("Cannot find minimal " + game.getBoardWithPossibleValues())
}
