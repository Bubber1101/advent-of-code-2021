package day2

import java.io.File

fun part1(): Int {
    var forward = 0
    var down = 0

    File("src/resources/inputs/day2.txt").useLines { lines ->
        lines.map { it.trimEnd().split(" ") }.forEach {
            when (it[0]) {
                "forward" -> forward += it[1].toInt()
                "down" -> down += it[1].toInt()
                "up" -> down -= it[1].toInt()
            }
        }
    }

    return forward * down
}

fun part2(): Int {
    var forward = 0
    var depth = 0
    var aim = 0

    File("src/resources/inputs/day2.txt").useLines { lines ->
        lines.map { it.trimEnd().split(" ") }.forEach {
            when (it[0]) {
                "forward" -> {
                    forward += it[1].toInt()
                    depth += it[1].toInt() * aim
                }
                "down" -> aim += it[1].toInt()
                "up" -> aim -= it[1].toInt()
            }
        }
    }
    return forward * depth

}
