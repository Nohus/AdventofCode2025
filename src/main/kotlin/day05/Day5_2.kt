package day05

import solveRaw

fun main() = solveRaw { input ->
    val rangesToAdd = input.substringBefore("\n\n").lines().map { line ->
        line.split("-").let { it[0].toLong()..it[1].toLong() }
    }.toMutableList()
    val nonOverlappingRanges = mutableListOf<LongRange>()

    outer@while (rangesToAdd.isNotEmpty()) {
        val new = rangesToAdd.removeFirst()
        for (existing in nonOverlappingRanges) {
            if (new.first <= existing.last && new.last >= existing.first) {
                nonOverlappingRanges -= existing
                rangesToAdd += minOf(new.first, existing.first)..maxOf(new.last, existing.last)
                continue@outer
            }
        }
        nonOverlappingRanges += new
    }
    nonOverlappingRanges.sumOf { it.last - it.first + 1 }
}
