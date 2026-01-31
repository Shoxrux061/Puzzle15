package com.uz.puzzle15.core.model

data class PuzzleModel(
    val size: Int,
    val cubes: List<List<Int>>
) {

    fun showEmpty(): Pair<Int, Int> {
        cubes.forEachIndexed { r, row ->
            row.forEachIndexed { c, value ->
                if (value == 16) return r to c
            }
        }
        error("Topilmadi")
    }

}