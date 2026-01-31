package com.uz.puzzle15.ui.game

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import com.uz.puzzle15.R
import com.uz.puzzle15.core.controller.GameController
import com.uz.puzzle15.core.model.PuzzleModel
import com.uz.puzzle15.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var controller: GameController

    lateinit var gameData: PuzzleModel

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        controller = GameController(this)
        gameData = controller.createLevel()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        loadDataToView()
        setActions()


    }

    private fun loadDataToView() {

        binding.cubesGroup.forEachIndexed { index, item ->

            val textView = item as TextView

            val row = index / gameData.size
            val col = index % gameData.size

            val value = gameData.cubes[row][col]

            Log.d("TAGValue", "loadDataToView: $value")


            if (value != 16) {
                textView.text = value.toString()
            } else {
                textView.visibility = View.INVISIBLE
            }
        }
    }

    private fun setActions() {

        binding.cubesGroup.forEachIndexed { index, view ->

            val row = index / gameData.size
            val col = index % gameData.size

            val value = gameData.showEmpty()

            view.setOnClickListener {
                controller.move(row, col)
            }
        }
    }
}