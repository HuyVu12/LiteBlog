package com.example.liteblog.utils.Functions

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant

class MyFunction {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        val instant = Instant.now()
        @RequiresApi(Build.VERSION_CODES.O)
        fun getCurrentTime() :Long{
            return instant.toEpochMilli()
        }
    }
}