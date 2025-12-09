package day09

import solve
import utils.Point
import kotlin.math.abs

fun main() = solve { lines ->
    val points = lines.map { it.split(",").let { Point(it[0].toInt(), it[1].toInt()) } }
    points.flatMap { a ->
        points.map { b ->
            val width = abs(a.x - b.x) + 1L
            val height = abs(a.y - b.y) + 1L
            width * height
        }
    }.max()
}
