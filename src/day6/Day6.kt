package day6

import java.io.File

val sums = mutableListOf<Int>()

fun part1(): Int {
    val inputs = File("src/resources/inputs/day6_test.txt")
        .readLines()[0]
        .split(",")
        .map { it.toInt() }

    sums.add(inputs.sum())
    var stepList = step(inputs)


    for (i in 0..80) {
        sums.add(stepList.sum())
        stepList = step(stepList)
    }

    return day6.sums[80]


}

fun get(index: Int): Int {
    return sums.getOrElse(index) { get(index - 7) + get(index - 9) }
}

fun step(list: List<Int>): List<Int> {
    val nextStep = mutableListOf<Int>()
    for (fish in list) {
        if (fish == 0) {
            nextStep.add(6)
            nextStep.add(8)
        } else nextStep.add(fish - 1)
    }
    return nextStep
}