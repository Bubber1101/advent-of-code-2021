package day4

import java.io.File

fun part1(): Int {
    val inputs: List<String> = File("src/resources/inputs/day4.txt").readLines()
    val inputNumbers = getInputNumbers(inputs)
    val boards = generateBoards(inputs)

    for (i in inputNumbers) {
        for (board in boards) {
            board.tick(i)
            if (board.checkForWin()) {
                return board.calculateScore(i)
            }
        }
    }

    return 0
}


fun part2(): Int {
    val inputs: List<String> = File("src/resources/inputs/day4.txt").readLines()
    val inputNumbers = getInputNumbers(inputs)
    val boards = generateBoards(inputs)
    var lastWon: Int = 0

    for (i in inputNumbers) {
        for (board in boards) {
            board.tick(i)
            if (!board.won and board.checkForWin()) {
                lastWon = board.calculateScore(i)
            }
        }
    }
    return lastWon
}

private fun getInputNumbers(inputs: List<String>) = inputs[0].split(",").map(String::toInt)

private fun generateBoards(inputs: List<String>) =
    inputs.drop(1).filter(String::isNotBlank).chunked(5).map(::BingoBoard)

class BingoBoard(inputs: List<String>) {
    var board = createBoard(inputs)
    var won = false

    fun calculateScore(winningNumber: Int): Int {
        var sum = 0
        board.forEach {
            it.forEach { cell ->
                if (!cell.ticked) sum += cell.number
            }
        }
//        println(winningNumber)
//        println(sum)
        return sum * winningNumber
    }

    fun tick(number: Int) {
        board.forEach {
            it.forEach { cell ->
                if (cell.number == number) tickCell(cell)
            }
        }
    }

    fun checkForWin(): Boolean {
        for (i in board.indices) { // assuming that  board is square
            if (checkRow(i) || checkColumn(i)) {
                won = true
                return true
            }
        }
        return false
    }

    fun checkRow(row: Int): Boolean {
        board[row].onEach { if (!it.ticked) return false }
        return true
    }

    fun checkColumn(column: Int): Boolean {
        board.forEach {
            if (!it[column].ticked) return false
        }
        return true
    }


    private fun createBoard(inputs: List<String>): List<List<Cell>> {
        val y = mutableListOf<MutableList<Cell>>()
        for (input in inputs) {
            y += input.chunked(3).map { it.trim().toInt() }.map { Cell(it) } as MutableList<Cell>
        }
        return y
    }

    data class Cell(val number: Int, var ticked: Boolean = false)

    fun tickCell(cell: Cell) {
        cell.ticked = true
    }
}