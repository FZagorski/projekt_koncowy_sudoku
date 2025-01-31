package com.example.sudoku

data class Sudoku(val board: Array<IntArray>, private val solution: Array<IntArray>) {
    private var hintsUsed = 0
    private var mistakes = 0

    fun getCell(index: Int): String {
        val row = index / 9
        val col = index % 9
        return if (board[row][col] == 0) "" else board[row][col].toString()
    }

    fun checkCell(index: Int, value: Int?): Boolean {
        if (value == null || value !in 1..9) return false
        val row = index / 9
        val col = index % 9

        return if (board[row][col] == 0 && solution[row][col] == value) {
            board[row][col] = value
            true
        } else {
            mistakes++
            false
        }
    }

    fun provideHint(): Boolean {
        val emptyCells = mutableListOf<Pair<Int, Int>>()
        for (row in 0 until 9) {
            for (col in 0 until 9) {
                if (board[row][col] == 0) {
                    emptyCells.add(row to col)
                }
            }
        }

        if (emptyCells.isNotEmpty()) {
            val (row, col) = emptyCells.random()
            board[row][col] = solution[row][col]
            hintsUsed++
            return true
        }
        return false
    }

    fun getMistakes(): Int = mistakes
    fun getHintsUsed(): Int = hintsUsed

    fun getSolution(): Array<IntArray> = solution // NOWA METODA
}
