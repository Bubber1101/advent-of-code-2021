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

fun part2(): Int {
    val inputs = File("src/resources/inputs/day7.txt")
        .readLines()[0]
        .split(",")
        .map { it.toInt() }

    var mean = findMean(inputs).toInt()

    return inputs.map { (0..abs(it - mean)).sum() }.sum()
}


//https://www.geeksforgeeks.org/program-for-mean-and-median-of-an-unsorted-array/ thx
fun findMean(a: List<Int>): Double {
    var sum = 0
    for (element in a) sum += element
    return sum.toDouble() / a.size.toDouble()
}

fun findMedian(a: List<Int>): Double {
    var a = a.sorted()
    var n = a.size
    return if (n % 2 != 0) a[n / 2].toDouble() else (a[(n - 1) / 2] + a[n / 2]).toDouble() / 2.0
}