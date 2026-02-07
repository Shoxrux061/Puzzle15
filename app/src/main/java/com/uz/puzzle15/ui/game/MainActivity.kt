package com.uz.puzzle15.ui.game

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEachIndexed
import androidx.lifecycle.lifecycleScope
import com.uz.puzzle15.core.controller.GameController
import com.uz.puzzle15.core.controller.TimeController
import com.uz.puzzle15.core.model.PuzzleModel
import com.uz.puzzle15.databinding.ActivityMainBinding
import com.uz.puzzle15.ui.result.ResultActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var controller: GameController
    lateinit var gameData: PuzzleModel

    lateinit var timeController: TimeController

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        controller = GameController()
        gameData = controller.getPuzzle()
        timeController = TimeController()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


        timeController.start(
            onTick = {
                lifecycleScope.launch {
                    setTimeText()
                }
            }
        )
        loadDataToView()
        setActions()


    }

    private fun setTimeText() {
        binding.timeText.text = timeController.getSecondsText()
    }

    private fun loadDataToView() {

        gameData = controller.getPuzzle()

        binding.cubesGroup.forEachIndexed { index, item ->

            val textView = item as TextView

            val row = index / gameData.size
            val col = index % gameData.size

            val value = gameData.cubes[row][col]

            if (value != 16) {
                textView.text = value.toString()
                textView.visibility = View.VISIBLE
            } else {
                textView.visibility = View.INVISIBLE
            }
        }
    }

    private fun setActions() {

        binding.cubesGroup.forEachIndexed { index, view ->

            val row = index / gameData.size
            val col = index % gameData.size

            view.setOnClickListener {
                controller.move(row, col)
                loadDataToView()
                if (controller.isFinish()) {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("finish_time", timeController.getSecond())
                    startActivity(intent)
                }
            }
        }
    }
}