package day02

import solve
import utils.findLongs
import utils.isEven

fun main() = solve { lines ->
    lines[0].replace("-", " ").findLongs().chunked(2).sumOf { range ->
        (range[0]..range[1]).sumOf { number ->
            val string = number.toString()
            val length = string.length
            if (length.isEven() && string.take(length / 2) == string.drop(length / 2)) number else 0
        }
    }
}
