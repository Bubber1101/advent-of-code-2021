package day7

import java.io.File
import kotlin.math.abs


fun part1(): Int {
    val inputs = File("src/resources/inputs/day7.txt")
        .readLines()[0]
        .split(",")
        .map { it.toInt() }

    var median = findMedian(inputs).toInt()

    return inputs.map { abs(it - median) }.sum()
}


fun findMedian(a: List<Int>): Double {
    var a = a.sorted()
    var n = a.size
    return if (n % 2 != 0) a[n / 2].toDouble() else (a[(n - 1) / 2] + a[n / 2]).toDouble() / 2.0
}