package com.example.sudoku

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.BaseAdapter

class SudokuAdapter(
    private val context: Context,
    private val sudoku: Sudoku,
    private val onCellChanged: (Int, Int) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = 81

    override fun getItem(position: Int): Any = sudoku.getCell(position)

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.sudoku_cell, parent, false)
        val editText = view.findViewById<EditText>(R.id.cellEditText)

        editText.setText(sudoku.getCell(position))

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val inputValue = s.toString().toIntOrNull()

                if (inputValue == null) return

                val row = position / 9
                val col = position % 9
                if (sudoku.board[row][col] == inputValue) return

                if (sudoku.checkCell(position, inputValue)) {
                    onCellChanged(position, inputValue)
                }

                notifyDataSetChanged()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        return view
    }
}
