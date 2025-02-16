package com.example.sudoku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.easyButton).setOnClickListener {
            startGame("easy")
        }
        findViewById<Button>(R.id.mediumButton).setOnClickListener {
            startGame("medium")
        }
        findViewById<Button>(R.id.hardButton).setOnClickListener {
            startGame("hard")
        }
    }

    private fun startGame(difficulty: String) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("difficulty", difficulty)
        startActivity(intent)
    }
}