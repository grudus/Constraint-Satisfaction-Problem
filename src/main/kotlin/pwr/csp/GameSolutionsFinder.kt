package pwr.csp

import pwr.csp.algorithm.SolutionDescription
import pwr.csp.algorithm.Solver
import pwr.csp.algorithm.heuristic.BoardPointSelector
import pwr.csp.games.Game

object GameSolutionsFinder {

    fun findSolutions(
            game: Game,
            solver: Solver,
            boardPointSelector: BoardPointSelector
    ): SolutionDescription =
            solver.solveAndMeasureTime(game, boardPointSelector)

}
