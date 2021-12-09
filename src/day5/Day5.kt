package day5

import java.io.File

fun part1(): Int {
    val inputs: List<String> = File("src/resources/inputs/day5.txt").readLines()
    val lines = getLines(inputs)
    val points = mutableListOf<Point>()
    var map = mutableMapOf<Point, Int>()
    for (line in lines) {
        for (point in line.points) {
            if (points.contains(point)) {
                points[points.indexOf(point)].increment()
            } else points.add(point)

        }
    }
    return points.count { it.count > 0 }
}

fun getLines(inputs: List<String>): List<Line> {
    val list = mutableListOf<Line>()

    for (input in inputs) {
        var points = input.split(" -> ").map {
            Point(it.split(",")[0].toInt(), it.split(",")[1].toInt())
        }
        list += Line(points[0], points[1])
    }
    return list;
}


class Line(val a: Point, val b: Point) {
    var points: List<Point> = getPoints(a, b)

    private fun getPoints(a: Point, b: Point): List<Point> {
        val list = mutableListOf<Point>()

        if (a.x == b.x) {
            var y = sortedSetOf(a.y, b.y)
            for (i in y.first()..y.last()) list.add(Point(a.x, i))

        } else if (a.y == b.y) {
            var x = sortedSetOf(a.x, b.x)
            for (i in x.first()..x.last()) list.add(Point(i, a.y))
        }
        return list
    }
}

data class Point(val x: Int, val y: Int, var count: Int = 0) {
    fun increment() {
        count++
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {

        var result = x
        result = 31 * result + y
        return result
    }


}