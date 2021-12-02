package day1

import java.io.File

/***
 * https://adventofcode.com/2021/day/1
 */
private fun readMeasurements(): List<Int> {
    val measurements = mutableListOf<Int>()
    File("src/resources/inputs/day1.txt")
        .useLines { lines ->
            lines.forEach {
                measurements.add(it.toInt())
            }
        }
    return measurements
}

fun part1(): Int {
    val measurements = readMeasurements()
    var numberOfIncreases = 0
    var previousMeasurement = measurements.first()

    for (measurement in measurements) {
        if (measurement > previousMeasurement) numberOfIncreases++
        previousMeasurement = measurement
    }
    return numberOfIncreases
}

fun part2(): Int {
    val measurements = readMeasurements()
    var numberOfIncreases = 0
    var slidingWindow = measurements.subList(0, 2).sum()
    var prevSlidingWindow: Int

    for (i in 3 until measurements.size) {
        prevSlidingWindow = slidingWindow
        slidingWindow = slidingWindow + measurements[i] - measurements[i - 3]
        if (slidingWindow > prevSlidingWindow) numberOfIncreases++
    }
    return numberOfIncreases
}
