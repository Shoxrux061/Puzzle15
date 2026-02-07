package com.uz.puzzle15.core.controller

import android.widget.Toast
import com.uz.puzzle15.core.model.PuzzleModel
import kotlin.math.abs

class GameController() {

    private var puzzle: PuzzleModel = createLevel()

    private val timerController = TimeController()

    fun getPuzzle(): PuzzleModel {
        return puzzle
    }

    fun createLevel(): PuzzleModel {

        val cubes = ArrayList<List<Int>>()

        val row = arrayListOf(1, 2, 3, 4)
        val row2 = arrayListOf(5, 6, 7, 8)
        val row3 = arrayListOf(9, 10, 11, 12)
        val row4 = arrayListOf(13, 14, 15, 16)
        cubes.add(row)
        cubes.add(row2)
        cubes.add(row3)
        cubes.add(row4)
        val puzzle = PuzzleModel(
            size = 4,
            cubes = cubes
        )
        return puzzle
    }

    fun move(row: Int, col: Int) {

        val emptyRow = puzzle.showEmpty().first
        val emptyColumn = puzzle.showEmpty().second

        val newPuzzle = puzzle.cubes.map { it.toMutableList() }

        if (!isNear(column = col, row = row, emptyColumn = emptyColumn, emptyRow = emptyRow)) return

        newPuzzle[emptyRow][emptyColumn] = puzzle.cubes[row][col]
        newPuzzle[row][col] = 16

        puzzle = PuzzleModel(
            size = 4,
            cubes = newPuzzle
        )

    }

    fun isFinish(): Boolean {

        return puzzle.cubes.flatten() == (1..16).toList()

    }

    fun isNear(column: Int, row: Int, emptyColumn: Int, emptyRow: Int): Boolean {

        val sameRow = row == emptyRow
        val sameColumn = column == emptyColumn

        val columnsNextToEachOther = abs(column - emptyColumn) == 1
        val rowsNextToEachOther = abs(row - emptyRow) == 1

        return (sameRow && columnsNextToEachOther) || (sameColumn && rowsNextToEachOther)
    }
}