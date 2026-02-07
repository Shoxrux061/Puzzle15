package com.uz.puzzle15.core.local_storage

import android.content.Context
import android.util.Log
import androidx.core.content.edit

class LocalStorage(context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("puzzle_cache", Context.MODE_PRIVATE)

    private val recordKey = "ROCORD_KEY"

    fun getRecord(): Int {

        return sharedPreferences.getInt(recordKey, 0)
    }

    fun setRecord(newRecord: Int) {
        Log.d("TAGNewRecord", "setRecord: $newRecord")
        sharedPreferences.edit { putInt(recordKey, newRecord) }
    }

}