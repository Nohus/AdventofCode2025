package day04

import solve
import utils.toGrid
import kotlin.collections.component1
import kotlin.collections.component2

fun main() = solve { lines ->
    val grid = lines.toGrid().toMutableMap()
    var count = 0
    while (true) {
        count += grid.count { (point, char) ->
            (char == '@' && point.getAdjacent().count { grid[it] == '@' } < 4).also {
                if (it)  grid[point] = '.'
            }
        }.also { if (it == 0) break }
    }
    count
}
