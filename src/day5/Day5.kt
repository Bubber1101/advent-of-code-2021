package day5

import java.io.File

fun part2(): Int {
    val inputs: List<String> = File("src/resources/inputs/day5.txt").readLines()
    val lines = getLines(inputs)
    val map = mutableMapOf<Point, Int>()
    for (line in lines) {
        for (point in line.points) {
            if (map.containsKey(point)) {
                map[point] = map[point]!! + 1
            } else map[point] = 0
        }
    }
    return map.count { it.value > 0 }
}

fun getLines(inputs: List<String>): List<Line> {
    val list = mutableListOf<Line>()

    for (input in inputs) {
        var points = input.split(" -> ").map {
            Point(it.split(",")[0].toInt(), it.split(",")[1].toInt())
        }
        list += Line(points[0], points[1])
    }
    return list
}


class Line(a: Point, b: Point) {
    var points: List<Point> = getPoints(a, b)

    private fun getPoints(a: Point, b: Point): List<Point> {
        val list = mutableListOf<Point>()

        if (a.x == b.x) {
            var y = sortedSetOf(a.y, b.y)
            for (i in y.first()..y.last()) list.add(Point(a.x, i))
        } else if (a.y == b.y) {
            var x = sortedSetOf(a.x, b.x)
            for (i in x.first()..x.last()) list.add(Point(i, a.y))
        } else {
            list += getDiagonalPointsBetween(a, b)
        }
        return list
    }
}

private fun getDiagonalPointsBetween(p1: Point, p2: Point): Set<Point> {
    val list = listOf(p1, p2).sortedBy { it.x }
    val points = mutableSetOf<Point>()
    val a = list[0]
    val b = list[1]

    var x = a.x
    var y = a.y
    if (a.y > b.y) {
        while (y >= b.y) {
            points.add(Point(x, y))
            y--
            x++
        }
    } else {
        while (y <= b.y) {
            points.add(Point(x, y))
            y++
            x++
        }
    }
    return points
}

data class Point(val x: Int, val y: Int)