package com.uz.puzzle15.core.controller

import android.util.Log
import java.util.Timer
import java.util.TimerTask

class TimeController {

    var seconds = 0
    var timer: Timer? = null

    fun start(
        onTick: () -> Unit
    ) {

        if (timer == null) {
            timer = Timer()
            timer?.schedule(
                object : TimerTask() {
                    override fun run() {
                        onTick()
                        seconds++
                        Log.d("TAGTimeController", "run: $seconds")
                    }

                }, 1000, 1000
            )
        }
    }

    fun getSecondsText(): String {
        val minut = seconds / 60
        val sec = seconds % 60

        val timeText = "$minut:$sec"
        return timeText
    }

    fun stop() {
        seconds = 0
        timer = null
    }

    fun getSecond(): Int {
        return seconds
    }
}