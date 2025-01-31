package com.example.sudoku

object SudokuGenerator {
    private const val SIZE = 9
    private val board = Array(SIZE) { IntArray(SIZE) }
    var solution = Array(SIZE) { IntArray(SIZE) }
        private set

    fun generateSudoku(): Array<IntArray> {
        fillGrid(board)
        solution = board.map { it.clone() }.toTypedArray()
        removeNumbers(board)
        return board
    }

    private fun fillGrid(grid: Array<IntArray>): Boolean {
        for (row in 0 until SIZE) {
            for (col in 0 until SIZE) {
                if (grid[row][col] == 0) {
                    val numbers = (1..9).shuffled()
                    for (num in numbers) {
                        if (isValid(grid, row, col, num)) {
                            grid[row][col] = num
                            if (fillGrid(grid)) return true
                            grid[row][col] = 0
                        }
                    }
                    return false
                }
            }
        }
        return true
    }

    private fun removeNumbers(grid: Array<IntArray>) {
        val emptyCells = (20..40).random()
        repeat(emptyCells) {
            var row: Int
            var col: Int
            do {
                row = (0 until SIZE).random()
                col = (0 until SIZE).random()
            } while (grid[row][col] == 0)
            grid[row][col] = 0
        }
    }

    public fun isValid(grid: Array<IntArray>, row: Int, col: Int, num: Int): Boolean {
        for (i in 0 until SIZE) {
            if (grid[row][i] == num || grid[i][col] == num) return false
        }
        val boxRow = (row / 3) * 3
        val boxCol = (col / 3) * 3
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (grid[boxRow + i][boxCol + j] == num) return false
            }
        }
        return true
    }
}


