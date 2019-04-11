package pwr.csp.algorithm

import pwr.csp.games.Game

data class SolutionDescription(
        private var movesToFirstSolution: Int? = null,
        private var totalMoves: Int = 0,
        private var numberOfSolutions: Int = 0,
        private var totalSeconds: Double = 0.0,
        private val solutions: MutableList<Game> = mutableListOf()
) {

    fun addMove() = apply {
        totalMoves++
    }

    fun withNewSolution(game: Game) = apply {
        if (movesToFirstSolution == null)
            movesToFirstSolution = totalMoves
        numberOfSolutions++
        solutions += game
    }

    fun withExecutionTime(seconds: Double) = apply {
        totalSeconds = seconds
    }

    fun solutions(): List<Game> = solutions

    override fun toString(): String {
        return "Number of solutions: $numberOfSolutions\n" +
                "Total time in seconds: $totalSeconds\n" +
                "Moves to first solution: $movesToFirstSolution\n" +
                "Total moves: $totalMoves\n"
    }
}
