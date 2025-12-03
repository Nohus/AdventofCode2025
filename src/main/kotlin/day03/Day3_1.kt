package day03

import solve

fun main() = solve { lines ->
    lines.sumOf { line ->
        val nums = mutableListOf<Int>()
        for (i1 in line.indices) {
            for (i2 in i1 + 1 until line.length) {
                nums += "${line[i1]}${line[i2]}".toInt()
            }
        }
        nums.max()
    }
}
