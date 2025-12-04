package day04

import solve
import utils.toGrid

fun main() = solve { lines ->
    val grid = lines.toGrid()
    grid.count { (point, char) ->
        char == '@' && point.getAdjacent().count { grid[it] == '@' } < 4
    }
}
