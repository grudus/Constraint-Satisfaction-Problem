package pwr.csp.algorithm.heuristic

import pwr.csp.commons.BoardPoint
import pwr.csp.games.Game

class MostConstrainedValueSelector : ValueSelector {

    override fun selectValue(game: Game): BoardPoint =
            game.getBoardWithPossibleValues().entries
                    .filter { (_, values) -> values.size > 1 }
                    .minBy { (_, values) -> values.size }
                    ?.key
                    ?: throw RuntimeException("Cannot find minimal " + game.getBoardWithPossibleValues())
}
