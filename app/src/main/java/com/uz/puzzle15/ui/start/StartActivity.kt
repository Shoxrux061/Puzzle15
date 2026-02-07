package com.uz.puzzle15.ui.start

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.uz.puzzle15.R
import com.uz.puzzle15.core.local_storage.LocalStorage
import com.uz.puzzle15.databinding.ActivityStartBinding
import com.uz.puzzle15.ui.game.MainActivity

class StartActivity : AppCompatActivity() {

    lateinit var binding: ActivityStartBinding
    lateinit var cache: LocalStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        cache = LocalStorage(this)
        enableEdgeToEdge()
        setContentView(binding.root)

        loadDataToView()
        setActions()

    }

    private fun loadDataToView() {

        val recordText = if (cache.getRecord() == 0) "no record" else cache.getRecord()
        binding.recordText.text = recordText.toString()

    }

    private fun setActions() {

        binding.playBtn.setOnClickListener {
            goToNextScreen(MainActivity())
        }

        binding.settingBtn.setOnClickListener {

        }

        binding.recordsBtn.setOnClickListener {

        }

    }

    private fun goToNextScreen(activity: Activity) {

        val intent = Intent(this, activity::class.java)
        startActivity(intent)

    }
}