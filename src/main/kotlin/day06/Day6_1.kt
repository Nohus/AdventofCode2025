package day06

import solve
import utils.findLongs
import utils.splitWords

fun main() = solve { lines ->
    val rows = lines.dropLast(1).map { it.findLongs() }
    val columns = List(rows.first().size) { index ->
        rows.map { it[index] } to lines.last().splitWords()[index]
    }
    columns.sumOf { (numbers, operator) ->
        if (operator == "+") numbers.sum() else numbers.reduce { acc, i -> acc * i }
    }
}
