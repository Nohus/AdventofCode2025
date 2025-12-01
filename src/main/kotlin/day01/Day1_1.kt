package day01

import solve

fun main() = solve { lines ->
    var dial = 50
    lines.count { line ->
        val direction = if (line[0] == 'R') 1 else -1
        val amount = line.drop(1).toInt() * direction
        dial += amount
        dial %= 100
        dial == 0
    }
}
