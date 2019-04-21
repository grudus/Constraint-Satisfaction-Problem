package pwr.csp

import pwr.csp.algorithm.ForwardCheckingSolver
import pwr.csp.algorithm.SolutionDescription
import pwr.csp.algorithm.Solver
import pwr.csp.algorithm.BacktrackingSolver
import pwr.csp.algorithm.heuristic.MostConstrainedBoardPointSelector
import pwr.csp.algorithm.heuristic.BoardPointSelector
import pwr.csp.algorithm.heuristic.MostLimitingBoardPointSelector
import pwr.csp.algorithm.heuristic.RandomBoardPointSelector
import pwr.csp.games.Futoshiki
import pwr.csp.games.Game
import pwr.csp.games.PossibleValuesGame
import pwr.csp.reader.FutoshikiReader
import pwr.csp.reader.GameReader
import pwr.csp.reader.SudokuReader
import java.io.File
import java.lang.IllegalArgumentException
import java.util.Arrays.toString
import java.util.concurrent.Executors
import java.util.concurrent.ExecutorService
import java.util.concurrent.atomic.AtomicInteger


private enum class SolverStrategy(val solver: Solver) {
    FORWARD_CHECKING(ForwardCheckingSolver()),
    BACKTRACKING(BacktrackingSolver()),
}

private enum class ValueSelectorStrategy(val boardPointSelector: BoardPointSelector) {
    MOST_CONSTRAINED(MostConstrainedBoardPointSelector()),
    MOST_LIMITING(MostLimitingBoardPointSelector()),
}


fun main(args: Array<String>) {

    val game = SudokuReader().read(File("res/research_data/sudoku/medium_1"))

    SolverStrategy.values().forEach { solver ->
        ValueSelectorStrategy.values().forEach { selector ->
            val solutionDescription: SolutionDescription = GameSolutionsFinder.findSolutions(
                    game,
                    solver.solver,
                    selector.boardPointSelector,
                    true
            )

            println(solutionDescription)
        }
    }

}
