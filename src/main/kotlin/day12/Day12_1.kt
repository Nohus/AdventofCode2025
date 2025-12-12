package day12

import solveRaw
import utils.findInts

fun main() = solveRaw { text ->
    text.split("\n\n").last().lines().count { line ->
        val squares = line.substringBefore(":").split("x").fold(1) { acc, string -> acc * string.toInt() / 3 }
        val presents = line.substringAfter(": ").findInts().sum()
        presents <= squares
    }
}
