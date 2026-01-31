package com.uz.puzzle15.core.controller

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.uz.puzzle15.core.model.PuzzleModel
import kotlin.math.abs
import kotlin.math.sign

class GameController(val context: Context) {

    private var cubes: PuzzleModel = createLevel()

    fun getCubes(): PuzzleModel {
        return cubes
    }

    fun createLevel(): PuzzleModel {

        val cubes = ArrayList<List<Int>>()

        val row = arrayListOf(1, 2, 3, 4).shuffled()
        val row2 = arrayListOf(5, 6, 7, 8).shuffled()
        val row3 = arrayListOf(9, 10, 11, 12).shuffled()
        val row4 = arrayListOf(13, 14, 15, 16).shuffled()
        cubes.add(row)
        cubes.add(row2)
        cubes.add(row3)
        cubes.add(row4)
        val puzzle = PuzzleModel(
            size = 4,
            cubes = cubes.shuffled()
        )
        return puzzle
    }

    fun move(row: Int, col: Int) {

        Toast.makeText(context, "$col", Toast.LENGTH_SHORT).show()
        val emptyRow = cubes.showEmpty().first
        val emptyColumn = cubes.showEmpty().second


        val newTiles = mutableListOf<MutableList<Int>>()

        for (r in 0 until cubes.size) {
            val newRow = mutableListOf<Int>()
            for (c in 0 until cubes.size) {
                when (r) {
                    row if c == col -> {
                        newRow.add(0)
                    }

                    emptyRow if c == emptyColumn -> {
                        newRow.add(cubes.cubes[row][col])
                    }

                    else -> {
                        newRow.add(cubes.cubes[r][c])
                    }
                }
            }
            newTiles.add(newRow)

            cubes = PuzzleModel(
                5,
                newTiles
            )

        }
    }

    fun isNear(column: Int, row: Int, emptyColumn: Int, emptyRow: Int): Boolean {

        val sameRow = row == emptyRow
        val sameColumn = column == emptyColumn

        val columnsNextToEachOther = abs(column - emptyColumn) == 1
        val rowsNextToEachOther = abs(row - emptyRow) == 1

        return (sameRow && columnsNextToEachOther) || (sameColumn && rowsNextToEachOther)
    }
}