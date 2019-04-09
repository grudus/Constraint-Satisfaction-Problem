package pwr.csp.algorithm.heuristic

import pwr.csp.algorithm.SolutionDescription
import pwr.csp.algorithm.Solver
import pwr.csp.commons.BoardPoint
import pwr.csp.games.Game

class BacktrackingSolver: Solver {

    override fun solve(game: Game, valueSelector: ValueSelector): SolutionDescription {
        return search(game, valueSelector, SolutionDescription())
    }

    private fun search(game: Game, valueSelector: ValueSelector, solutionDescription: SolutionDescription): SolutionDescription {
        if (!game.isBoardValid()) {
            return solutionDescription
        }

        if (game.isCompleted()) {
            return solutionDescription.addSolution(game)
        }

        val position: BoardPoint = valueSelector.selectValue(game)

        game.findPossibleValues(position).forEach { value ->
            val updatedGame = game.update(position, value)

            search(updatedGame, valueSelector, solutionDescription.addMove())
        }

        return solutionDescription
    }

}
