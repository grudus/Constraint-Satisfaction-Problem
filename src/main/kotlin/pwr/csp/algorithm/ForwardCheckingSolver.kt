package pwr.csp.algorithm

import pwr.csp.algorithm.heuristic.ValueSelector
import pwr.csp.games.Game

class ForwardCheckingSolver : Solver {

    override fun solve(game: Game, valueSelector: ValueSelector): List<Game> {
        return search(game, valueSelector, hashSetOf()).toList()
    }

    private fun search(game: Game, valueSelector: ValueSelector, foundSolutions: MutableSet<Game>): Set<Game> {
        if (!game.isBoardValid())
            return foundSolutions

        if (game.isCompleted()) {
            return foundSolutions + game
        }

        val selectedPoint = valueSelector.selectValue(game)

        game.findPossibleValues(selectedPoint).forEach { value ->
            val updatedGame = game.update(selectedPoint, value).eliminateInconsistentValues()
            val result = search(updatedGame, valueSelector, foundSolutions)

            if (result.isNotEmpty() && result.all { it.isCompleted() }) {
                foundSolutions += result
            }
        }

        return foundSolutions
    }

}
