package day02

import solve
import utils.findLongs
import kotlin.collections.chunked

fun main() = solve { lines ->
    lines[0].replace("-", " ").findLongs().chunked(2).sumOf { range ->
        (range[0]..range[1]).sumOf { number ->
            val string = number.toString()
            for (chunk in 1..(string.length / 2)) {
                if (string.chunked(chunk).all { it == string.take(chunk) }) {
                    return@sumOf number
                }
            }
            0
        }
    }
}
