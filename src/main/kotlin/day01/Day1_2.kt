package day01

import solve

fun main() = solve { lines ->
    var dial = 50
    var count = 0
    lines.forEach { line ->
        val direction = if (line[0] == 'R') 1 else -1
        val amount = line.drop(1).toInt()
        repeat(amount) {
            dial += direction
            dial %= 100
            if (dial == 0) count++
        }
    }
    count
}
