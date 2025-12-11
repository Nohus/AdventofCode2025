package day11

import solve

fun main() = solve { lines ->
    val outputs = lines.map {
        it.substringBefore(": ") to it.substringAfter(": ").split(" ")
    }.toMap()

    fun count(device: String): Int {
        return outputs[device]!!.sumOf { output ->
            if (output == "out") 1 else count(output)
        }
    }

    count("you")
}
