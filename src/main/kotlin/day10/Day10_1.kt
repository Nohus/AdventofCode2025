package day10

import solve
import utils.findInts
import java.util.PriorityQueue

fun main() = solve { lines ->
    lines.sumOf { solveLine(it) }
}

private fun solveLine(line: String): Int {
    val lightsTarget = line.substringBefore("]").removePrefix("[").map { it == '#' }
    val buttons = line.substringAfter("] ").substringBefore(" {").split(" ").map { button ->
        button.removeSurrounding("(", ")").findInts()
    }

    data class Node(
        val distance: Int,
        val lights: List<Boolean>
    )

    val queue = PriorityQueue<Node> { a, b -> a.distance - b.distance }
    queue.add(Node(0, List(lightsTarget.size) { false }))
    while (queue.isNotEmpty()) {
        val (distance, lights) = queue.poll()
        if (lightsTarget == lights) return distance
        buttons.forEach { button ->
            queue.add(Node(distance + 1, lights.mapIndexed { i, l -> if (i in button) !l else l }))
        }
    }
    throw IllegalStateException("No solution found")
}
