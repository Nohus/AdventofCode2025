package day09

import solve
import utils.Point

fun main() = solve { lines ->
    val points = lines.map { it.split(",").let { Point(it[0].toInt(), it[1].toInt()) } }
    val lines = (points + points.first()).windowed(2).map { it[0] to it[1] }

    var max = 0L
    for (a in points) {
        for (b in points - a) {
            // Inner rectangle bounds
            val xMin = minOf(a.x, b.x) + 1
            val xMax = maxOf(a.x, b.x) - 1
            val yMin = minOf(a.y, b.y) + 1
            val yMax = maxOf(a.y, b.y) - 1

            val width = xMax - xMin + 1 + 2L
            val height = yMax - yMin + 1 + 2L
            val area = width * height
            if (area <= max) continue

            val isCrossing = listOf(
                Point(xMin, yMin) to Point(xMax, yMin),
                Point(xMax, yMin) to Point(xMax, yMax),
                Point(xMax, yMax) to Point(xMin, yMax),
                Point(xMin, yMax) to Point(xMin, yMin),
            ).any { lineA ->
                val isAHorizontal = lineA.first.y == lineA.second.y
                lines.any { lineB ->
                    val isBHorizontal = lineB.first.y == lineB.second.y
                    if (isAHorizontal && !isBHorizontal) {
                        // Check for vertical crossing
                        val xMinA = minOf(lineA.first.x, lineA.second.x)
                        val xMaxA = maxOf(lineA.first.x, lineA.second.x)
                        val yMinB = minOf(lineB.first.y, lineB.second.y)
                        val yMaxB = maxOf(lineB.first.y, lineB.second.y)
                        val xB = lineB.first.x
                        val aY = lineA.first.y
                        xB in xMinA..xMaxA && aY in yMinB..yMaxB
                    } else if (!isAHorizontal && isBHorizontal) {
                        // Check for horizontal crossing
                        val yMinA = minOf(lineA.first.y, lineA.second.y)
                        val yMaxA = maxOf(lineA.first.y, lineA.second.y)
                        val xMinB = minOf(lineB.first.x, lineB.second.x)
                        val xMaxB = maxOf(lineB.first.x, lineB.second.x)
                        val yB = lineB.first.y
                        val aX = lineA.first.x
                        yB in yMinA..yMaxA && aX in xMinB..xMaxB
                    } else false
                }
            }
            if (isCrossing) continue
            max = area
        }
    }
    max
}
