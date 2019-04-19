package pwr.csp.algorithm

import pwr.csp.algorithm.heuristic.BoardPointSelector
import pwr.csp.games.Game

class ForwardCheckingSolver : Solver {

    override fun solve(game: Game, boardPointSelector: BoardPointSelector, searchAll: Boolean): SolutionDescription {
        return search(game.eliminateInconsistentValues(), boardPointSelector, SolutionDescription(), searchAll)
    }

    private fun search(game: Game, boardPointSelector: BoardPointSelector, solutionDescription: SolutionDescription, searchAll: Boolean): SolutionDescription {
        if (!game.isBoardValid())
            return solutionDescription.withNewReturn()

        if (game.isCompleted()) {
            return solutionDescription.withNewSolution(game)
        }

        val boardPoint = boardPointSelector.select(game)

        game.findPossibleValues(boardPoint).forEach { value ->
            val updatedGame = game.update(boardPoint, value).eliminateInconsistentValues()

            val result = search(updatedGame, boardPointSelector, solutionDescription.addMove(), searchAll)

            if (!searchAll && result.solutions().isNotEmpty()) {
                return result
            }
        }

        return solutionDescription
    }

}
