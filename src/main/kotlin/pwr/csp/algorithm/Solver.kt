package pwr.csp.algorithm

import pwr.csp.algorithm.heuristic.ValueSelector
import pwr.csp.games.Game

interface Solver {
    fun solve(game: Game, valueSelector: ValueSelector): SolutionDescription
}
