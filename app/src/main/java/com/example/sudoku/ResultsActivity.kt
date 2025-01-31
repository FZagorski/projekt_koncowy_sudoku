package com.example.sudoku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val elapsedTime = intent.getLongExtra("elapsedTime", 0L)
        val mistakes = intent.getIntExtra("mistakes", 0)
        val hintsUsed = intent.getIntExtra("hintsUsed", 0)

        val timeTextView: TextView = findViewById(R.id.timeTextView)
        val mistakesTextView: TextView = findViewById(R.id.mistakesTextView)
        val hintsTextView: TextView = findViewById(R.id.hintsTextView)

        val minutes = (elapsedTime / 1000) / 60
        val seconds = (elapsedTime / 1000) % 60
        timeTextView.text = "Czas: $minutes:$seconds"

        mistakesTextView.text = "Błędy: $mistakes"
        hintsTextView.text = "Podpowiedzi: $hintsUsed"

        val backToMenuButton: Button = findViewById(R.id.backToMenuButton)
        backToMenuButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}