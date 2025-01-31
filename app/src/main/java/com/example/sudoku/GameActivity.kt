package com.example.sudoku

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    private lateinit var sudoku: Sudoku
    private lateinit var sudokuAdapter: SudokuAdapter
    private lateinit var chronometer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        sudoku = Sudoku(SudokuGenerator.generateSudoku(), SudokuGenerator.solution)
        sudokuAdapter = SudokuAdapter(this, sudoku) { index, value -> onCellChanged(index, value) }

        val gridView: GridView = findViewById(R.id.gridView)
        gridView.adapter = sudokuAdapter

        chronometer = findViewById(R.id.gameChronometer)
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()

        val hintButton: Button = findViewById(R.id.hintButton)
        hintButton.setOnClickListener {
            provideHint()
        }

        val finishGameButton: Button = findViewById(R.id.finishButton)
        finishGameButton.setOnClickListener {
            endGame()
        }
    }

    private fun provideHint() {
        if (sudoku.provideHint()) {
            Toast.makeText(this, "Podpowiedź udzielona!", Toast.LENGTH_SHORT).show()
            sudokuAdapter.notifyDataSetChanged() // Odświeżenie adaptera, aby pokazać nową wartość
        } else {
            Toast.makeText(this, "Brak pustych komórek do podpowiedzi!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isBoardCompleted(): Boolean {
        for (row in 0 until 9) {
            for (col in 0 until 9) {
                if (sudoku.board[row][col] == 0) {
                    return false
                }
            }
        }
        return true
    }

    private fun checkGameCompletion() {
        if (isBoardCompleted()) {
            chronometer.stop()
            val elapsedMillis = SystemClock.elapsedRealtime() - chronometer.base

            val intent = Intent(this, ResultsActivity::class.java)
            intent.putExtra("elapsedTime", elapsedMillis)
            intent.putExtra("mistakes", sudoku.getMistakes())
            intent.putExtra("hintsUsed", sudoku.getHintsUsed())
            startActivity(intent)
            finish()
        }
    }

    private fun onCellChanged(index: Int, value: Int) {
        if (sudoku.checkCell(index, value)) {
            checkGameCompletion()
        }
    }

    private fun endGame() {
        chronometer.stop()
        val elapsedMillis = SystemClock.elapsedRealtime() - chronometer.base

        val intent = Intent(this, ResultsActivity::class.java)
        intent.putExtra("elapsedTime", elapsedMillis)
        intent.putExtra("mistakes", sudoku.getMistakes())
        intent.putExtra("hintsUsed", sudoku.getHintsUsed())
        startActivity(intent)
        finish()
    }
}



