package pwr.csp.algorithm

import pwr.csp.algorithm.heuristic.ValueSelector
import pwr.csp.games.Game
import java.util.*

class ForwardCheckingSolver : Solver {

    override fun solve(game: Game, valueSelector: ValueSelector): SolutionDescription {
        val startTime = Date()
        val result = search(game.eliminateInconsistentValues(), valueSelector, SolutionDescription())
        val seconds = (Date().time - startTime.time) / 1000.0

        return result.withExecutionTime(seconds)
    }

    private fun search(game: Game, valueSelector: ValueSelector, solutionDescription: SolutionDescription): SolutionDescription {
        if (!game.isBoardValid())
            return solutionDescription

        if (game.isCompleted()) {
            return solutionDescription.addSolution(game)
        }

        val selectedPoint = valueSelector.selectValue(game)

        game.findPossibleValues(selectedPoint).forEach { value ->
            solutionDescription.addMove()
            val updatedGame = game.update(selectedPoint, value).eliminateInconsistentValues()
            search(updatedGame, valueSelector, solutionDescription)
        }

        return solutionDescription
    }

}
