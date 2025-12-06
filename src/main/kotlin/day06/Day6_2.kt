package day06

import solve

fun main() = solve(trim = false) { lines ->
    val columns = List(lines.maxOf { it.length }) { inputColumn ->
        lines.map { it.getOrNull(inputColumn) ?: "" }.joinToString("").trim()
    } + listOf("")
    val problem = mutableListOf<String>()
    columns.sumOf { column ->
        if (column.isNotBlank()) {
            problem += column
            0
        } else {
            val operator = problem.first().last()
            val numbers = problem.drop(1).map { it.toLong() } + problem.first().dropLast(1).trim().toLong()
            problem.clear()
            if (operator == '+') numbers.sum() else numbers.reduce { acc, i -> acc * i }
        }
    }
}
