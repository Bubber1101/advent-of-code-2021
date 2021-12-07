package day3

import java.io.File

fun part1(): Int {
    var inputs: List<String> = File("src/resources/inputs/day3.txt").readLines()
    var bits = generateSummary(inputs)

    var gammaBits = ""
    bits.forEach { if (it > 0) gammaBits += "1" else gammaBits += "0" }
    var flippedGammaBits = gammaBits.map {
        if (it == '1') '0' else '1'
    }
    var gammaDec = convertBinaryToDecimal(gammaBits.toLong())
    var epsilonDec = convertBinaryToDecimal(flippedGammaBits.joinToString("").toLong())
    return gammaDec * epsilonDec
}

fun part2(): Int {
    val inputs: List<String> = File("src/resources/inputs/day3.txt").readLines()

    var oxygenRating = getRating(inputs, true)
    var co2Rating = getRating(inputs, false)

    return oxygenRating * co2Rating
}

fun getRating(inputs: List<String>, forOxygen: Boolean): Int {
    var temp = inputs
    var preferredBit = if (forOxygen) 0 else 1
    var otherBit = if (forOxygen) 1 else 0
    for (i in 0..inputs[0].length) {
        if (temp.size > 1) {
            temp =
                temp.filter { it[i].toString().toInt() == criterion(generateSummary(temp)[i], preferredBit, otherBit) }
        }
    }
    return convertBinaryToDecimal(temp[0].toLong())
}

fun generateSummary(inputs: List<String>): IntArray {
    var summary = IntArray(inputs.first().length)

    for (i in inputs) {

        i.trimEnd().split("")
        for (j in i.indices) {
            when (i[j]) {
                '1' -> summary[j] += 1
                '0' -> summary[j] -= 1
            }
        }
    }
    return summary
}

fun criterion(value: Int, preferredBit: Int, otherBit: Int): Int {
    return if (value < 0) preferredBit else otherBit
}

/**
 * https://www.programiz.com/kotlin-programming/examples/binary-decimal-convert thx
 */
fun convertBinaryToDecimal(num: Long): Int {
    var num = num
    var decimalNumber = 0
    var i = 0
    var remainder: Long

    while (num.toInt() != 0) {
        remainder = num % 10
        num /= 10
        decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
        ++i
    }
    return decimalNumber

    return 0
}