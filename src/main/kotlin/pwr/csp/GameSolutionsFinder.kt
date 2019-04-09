package pwr.csp

import pwr.csp.algorithm.SolutionDescription
import pwr.csp.algorithm.Solver
import pwr.csp.algorithm.heuristic.ValueSelector
import pwr.csp.games.Game

object GameSolutionsFinder {

    fun findSolutions(
            game: Game,
            solver: Solver,
            valueSelector: ValueSelector
    ): SolutionDescription =
            solver.solveAndMeasureTime(game, valueSelector)

}
