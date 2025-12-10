package day10

import com.microsoft.z3.Context
import com.microsoft.z3.IntNum
import solve
import utils.findInts

fun main() = solve { lines ->
    lines.sumOf { line ->
        Context().use { ctx ->
            val buttons = line.substringAfter("] ").substringBefore(" {")
                .split(" ").mapIndexed { index, button -> button.findInts() to ctx.mkIntConst("$index") }
            val joltageTarget = line.substringAfter("{").substringBefore("}").findInts()
            val buttonConstants = buttons.map { (_, constant) -> constant }
            val optimize = ctx.mkOptimize()
            optimize.MkMinimize(ctx.mkAdd(*buttonConstants.toTypedArray()))
            joltageTarget.withIndex().forEach { (index, target) ->
                val matchingButtons = buttons.filter { index in it.first }.map { it.second }
                optimize.Add(ctx.mkEq(ctx.mkAdd(*matchingButtons.toTypedArray()), ctx.mkInt(target)))
            }
            buttonConstants.forEach { optimize.Add(ctx.mkGe(it, ctx.mkInt(0))) }
            optimize.Check()
            optimize.model.decls.sumOf { (optimize.model.eval(it.apply(), true) as IntNum).int }
        }
    }
}
