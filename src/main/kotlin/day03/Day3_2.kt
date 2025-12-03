package day03

import solve

fun main() = solve { lines ->
    lines.sumOf { line ->
        var number = ""
        var lastDigitIndex = -1
        for (digitIndex in 0 until 12) {
            val (index, digit) = line
                .withIndex()
                .drop(lastDigitIndex + 1)
                .dropLast(11 - digitIndex)
                .maxBy { (_, digit) -> digit.digitToInt() }
            lastDigitIndex = index
            number += digit
        }
        number.toLong()
    }
}
