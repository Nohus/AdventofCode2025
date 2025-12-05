package day05

import solveRaw

fun main() = solveRaw { input ->
    val (rangesInput, idsInput) = input.split("\n\n")
    val ranges = rangesInput.lines().map { line ->
        line.split("-").let { it[0].toLong()..it[1].toLong() }
    }
    val ids = idsInput.lines().map { it.toLong() }
    ids.count { id -> ranges.any { id in it } }
}
