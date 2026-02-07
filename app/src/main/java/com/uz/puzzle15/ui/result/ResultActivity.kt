package com.uz.puzzle15.ui.result

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.uz.puzzle15.R
import com.uz.puzzle15.core.local_storage.LocalStorage
import com.uz.puzzle15.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityResultBinding

    lateinit var cache: LocalStorage

    var finishedTime = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityResultBinding.inflate(layoutInflater)
        cache = LocalStorage(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        getArgument()
        loadDataToView()


    }

    private fun getArgument() {
        finishedTime = intent.getIntExtra("finish_time", 0)

        if (finishedTime < cache.getRecord()) {
            Log.d("TAGUpdateRecrod", "getArgument: record updated ")

        }

        cache.setRecord(finishedTime)
    }

    private fun loadDataToView() {
        binding.finishText.text = "You finished at $finishedTime"
    }

}