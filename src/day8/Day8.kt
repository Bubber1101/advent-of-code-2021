package day8

import java.io.File

fun part1(): Int {
    val inputs: List<String> = File("src/resources/inputs/day8.txt").readLines()

    val uniqueComboLengthList = listOf(2, 3, 4, 7)
    var uniqueComboCount = 0
    for (input in inputs) {
        var outputs = input.split(" | ")[1].split(" ")
        for (output in outputs) {
            if (output.length in uniqueComboLengthList) {
                uniqueComboCount++
            }
        }
    }
    return uniqueComboCount
}

fun part2(): Int {
    val inputs: List<String> = File("src/resources/inputs/day8.txt").readLines()
    var sum = 0
    val uniqueComboLengthList = listOf(2, 3, 4, 7)
    for (input in inputs) {
        var numberMap = mutableMapOf<Int, MutableSet<Set<String>>>()
        var finalMap = mutableMapOf<Int, Set<String>>()
        val inputArray = input.split(" | ")
        var combinations = inputArray[0].split(" ")
        var outputs = inputArray[1].split(" ")
        for (combination in combinations) {
            var set = numberMap.getOrDefault(combination.length, HashSet())
            set.add(combination.split("").sorted().toSet())
            numberMap.put(combination.length, set)
        }

        finalMap.put(1, numberMap.get(2)!!.first())
        finalMap.put(4, numberMap.get(4)!!.first())
        finalMap.put(7, numberMap.get(3)!!.first())
        finalMap.put(8, numberMap.get(7)!!.first())

        finalMap.put(5, numberMap.get(5)!!.filter { it.containsAll(finalMap.get(4)!!.minus(finalMap.get(1)!!.asSequence())) }.first())
        finalMap.put(3, numberMap.get(5)!!.filter { it.containsAll(finalMap[7]!!.toList()) }.first())
        finalMap.put(2, numberMap.get(5)!!.filter {it.toString() != finalMap[3].toString()}.filter {it.toString() != finalMap[5].toString()}.first())


        finalMap.put(0, numberMap.get(6)!!.filter { finalMap[4]!!.minus(it.asSequence()).minus(finalMap[1]!!.asSequence()).size.equals(1) }.first())
        finalMap.put(6, numberMap.get(6)!!.filter { finalMap[8]!!.minus(it.asSequence()).minus(finalMap[1]!!.asSequence()).size.equals(0)}.first())
        finalMap.put(9, numberMap.get(6)!!.filter {it.toString() != finalMap[0].toString()}.filter {it.toString() != finalMap[6].toString()}.first())
        var outcome = outputs.stream().map {
                output -> finalMap.filterValues {  it.toString() == output.split("").toSet().sorted().toString()}.keys.first().toString() }
            .reduce { left, right -> left + right }.get().toInt()
        sum += outcome
    }
    return sum
}
