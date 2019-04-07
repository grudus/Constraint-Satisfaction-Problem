package pwr.csp.reader

import pwr.csp.commons.Board
import pwr.csp.commons.BoardPoint
import pwr.csp.games.Futushiki
import pwr.csp.games.GreaterThanRelation
import pwr.csp.printer.FutushikiBoardPrinter
import java.io.File

class FutushikiReader : GameReader<Futushiki> {

    override fun read(file: File): Futushiki {
        val lines = file.readLines()
        val boardSize = lines[0].toInt()

        val rawBoard = lines.subList(2, 2 + boardSize)
                .map { row -> row.split(";").map(String::toInt) }

        val relations = lines.drop(3 + boardSize)
                .map { row -> row.split(";").map { BoardPoint(it) } }
                .map { GreaterThanRelation(it[1], it[0]) }

        val board: Board<List<Int>> = ('A' until 'A' + boardSize).mapIndexed { rowIndex, row ->
            (1..boardSize).map { col ->
                val number = rawBoard[rowIndex][col - 1]
                val possibleValues = if (number == 0) (1..boardSize).toList() else listOf(number)
                Pair(BoardPoint(row, col), possibleValues)
            }
        }.flatten()
                .groupBy({ (point, _) -> point}, { (_, value) -> value })
                .mapValues { it.value[0] }

        return Futushiki(board, relations, FutushikiBoardPrinter())
    }
}
