package day07

import solve
import utils.Direction.EAST
import utils.Direction.SOUTH
import utils.Direction.WEST
import utils.Point
import utils.toGrid

fun main() = solve { lines ->
    val grid = lines.toGrid()
    val cache = mutableMapOf<Point, Long>()

    fun count(beam: Point): Long {
        cache[beam]?.let { return it }
        val next = beam.move(SOUTH)
        return when (grid[next]) {
            '.' -> count(next)
            '^' -> count(next.move(WEST)) + count(next.move(EAST))
            else -> 1
        }.also { cache[beam] = it }
    }

    count(grid.entries.first { (_, char) -> char == 'S' }.key)
}
