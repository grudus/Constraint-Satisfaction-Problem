package pwr.csp.algorithm

import pwr.csp.algorithm.heuristic.ValueSelector
import pwr.csp.games.Game
import java.util.*

interface Solver {
    fun solve(game: Game, valueSelector: ValueSelector): SolutionDescription

    fun solveAndMeasureTime(game: Game, valueSelector: ValueSelector): SolutionDescription {
        val start = Date()
        val result = solve(game, valueSelector)
        val executionTimeInSeconds = (Date().time - start.time) / 1000.0
        return result.withExecutionTime(executionTimeInSeconds)
    }
}
