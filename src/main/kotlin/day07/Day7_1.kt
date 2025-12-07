package day07

import solve
import utils.Direction.EAST
import utils.Direction.SOUTH
import utils.Direction.WEST
import utils.toGrid

fun main() = solve { lines ->
    val grid = lines.toGrid()
    val start = grid.entries.first { (_, char) -> char == 'S' }.key
    val beams = mutableSetOf(start)
    var count = 0

    while (beams.isNotEmpty()) {
        beams.toList().forEach { beam ->
            beams -= beam
            val next = beam.move(SOUTH)
            when (grid[next]) {
                '.' -> beams += next
                '^' -> {
                    count++
                    beams += next.move(WEST)
                    beams += next.move(EAST)
                }
            }
        }
    }

    count
}
