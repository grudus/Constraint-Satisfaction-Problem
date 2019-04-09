package pwr.csp.algorithm

import pwr.csp.algorithm.heuristic.BoardPointSelector
import pwr.csp.games.Game
import java.util.*

interface Solver {
    fun solve(game: Game, boardPointSelector: BoardPointSelector): SolutionDescription

    fun solveAndMeasureTime(game: Game, boardPointSelector: BoardPointSelector): SolutionDescription {
        val start = Date()
        val result = solve(game, boardPointSelector)
        val executionTimeInSeconds = (Date().time - start.time) / 1000.0
        return result.withExecutionTime(executionTimeInSeconds)
    }
}
