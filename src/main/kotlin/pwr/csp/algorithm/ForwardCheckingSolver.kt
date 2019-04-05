package pwr.csp.algorithm

import pwr.csp.algorithm.heuristic.ValueSelector
import pwr.csp.games.Game

class ForwardCheckingSolver: Solver {

    override fun solve(game: Game, valueSelector: ValueSelector): List<Game> {
        return search(game, valueSelector)
    }

    private fun search(game: Game, valueSelector: ValueSelector): List<Game> {
        if (!game.isBoardValid())
            return emptyList()

        if (game.isCompleted())
            return listOf(game)

        val selectedPoint = valueSelector.selectValue(game)

        game.findPossibleValues(selectedPoint).forEach { value ->
            val updatedGame = game.update(selectedPoint, value).eliminateInconsistentValues()

            val result = search(updatedGame, valueSelector)

            if (result.isNotEmpty()) {
                return result
            }
        }

        return emptyList()
    }

}
