package pwr.csp.io

import java.io.File

interface GameReader<GAME> {
    fun read(file: File): GAME
}
