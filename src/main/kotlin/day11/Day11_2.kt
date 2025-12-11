package day11

import solve

fun main() = solve { lines ->
    val outputs = lines.map {
        it.substringBefore(": ") to it.substringAfter(": ").split(" ")
    }.toMap()

    data class Key(val device: String, val hasFft: Boolean, val hasDac: Boolean)
    val cache = mutableMapOf<Key, Long>()
    fun count(key: Key): Long {
        cache[key]?.let { return it }
        return outputs[key.device]!!.sumOf { output ->
            if (output == "out") if (key.hasFft && key.hasDac) 1 else 0
            else count(Key(output, key.hasFft || output == "fft", key.hasDac || output == "dac"))
        }.also { cache[key] = it }
    }

    count(Key("svr", false, false))
}
