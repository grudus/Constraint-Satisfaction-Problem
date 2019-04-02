package pwr.csp.reader

import java.io.File

interface GameReader<GAME> {
    fun read(file: File): GAME
}
