package pwr.csp.commons

data class BoardPoint(val row: Char, val col: Int) : Comparable<BoardPoint> {
    constructor(string: String) : this(string[0], string.drop(1).toInt())

    override fun toString() = "$row$col"

    override fun compareTo(other: BoardPoint): Int {
        return if (row == other.row)
            col.compareTo(other.col)
        else row.compareTo(other.row)
    }
}
