package day6

import java.io.File

val sums = mutableListOf<Long>()

fun getFishes(onDay: Int): Long {
    val inputs = File("src/resources/inputs/day6.txt")
        .readLines()[0]
        .split(",")
        .map { it.toLong() }

    sums.add(inputs.count().toLong())
    var stepList = step(inputs)

    //for filling out the sums list, later the more is done here the less recursive calls in the #get function
    for (i in 2..50) {
        sums.add(stepList.count().toLong())
        stepList = step(stepList)
    }

    return get(onDay)
}

fun get(index: Int): Long {
    return sums.getOrElse(index) { get(index - 7) + get(index - 9) }
}

fun step(list: List<Long>): List<Long> {
    val nextStep = mutableListOf<Long>()
    for (fish in list) {
        if (fish == 0L) {
            nextStep.add(6)
            nextStep.add(8)
        } else nextStep.add(fish - 1)
    }
    return nextStep
}