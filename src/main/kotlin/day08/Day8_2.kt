package day08

import solve
import utils.Vector3

fun main() = solve { lines ->
    val allBoxes = lines.map {
        it.split(",").map { it.toInt() }.let { Vector3(it[0], it[1], it[2]) }
    }
    val pairs = allBoxes.flatMap { a ->
        (allBoxes - a).map { b ->
            listOf(a, b).sortedWith(compareBy({ it.x }, { it.y }, { it.z }))
                .let { it[0] to it[1] } to a.distance(b)
        }
    }.toMap().entries.sortedBy { it.value }.map { it.key }

    val circuits = mutableMapOf<Vector3, List<Vector3>>()
    for ((boxA, boxB) in pairs) {
        val circuitOfA = circuits[boxA] ?: setOf(boxA)
        val circuitOfB = circuits[boxB] ?: setOf(boxB)
        if (boxA !in circuitOfB) {
            circuitOfA.forEach { inCircuit ->
                circuits[inCircuit] = (circuits[inCircuit] ?: setOf(inCircuit)) + circuitOfB
            }
            circuitOfB.forEach { inCircuit ->
                circuits[inCircuit] = (circuits[inCircuit] ?: setOf(inCircuit)) + circuitOfA
            }
            if (circuits[boxA]!!.size == allBoxes.size) return@solve boxA.x * boxB.x
        }
    }
}
