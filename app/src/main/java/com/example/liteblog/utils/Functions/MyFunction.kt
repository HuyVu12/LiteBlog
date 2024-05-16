package com.example.liteblog.utils.Functions

import com.example.liteblog.utils.Model.DateTime
import java.time.Instant
import java.time.LocalDateTime

class MyFunction {
    companion object {
        var instant = Instant.now()
        fun getCurrentTime() :Long{
            instant = Instant.now()
            return instant.toEpochMilli()
        }
        fun getDateTimeFromMilli(millis: Long): DateTime {
            val instant = Instant.ofEpochMilli(millis)
            val datetime = LocalDateTime.from(instant)
            val dateTime = DateTime(
                year = datetime.year,
                month = datetime.monthValue * 1L,
                day = datetime.dayOfMonth * 1L,
                hour = datetime.hour * 1L,
                minute = datetime.minute * 1L,
                second = datetime.second * 1L
            )
            return dateTime
        }
        fun getTimePastFromNow(millis: Long): DateTime {
            val timePastMillis = getCurrentTime() - millis
            var seconds = timePastMillis / 1000
            var minute = seconds / 60; seconds %= 60
            var hour = minute / 60; minute %= 60
            var days = hour / 24; hour %= 24
            return DateTime(second = seconds, minute = minute, hour = hour, day = days, month = 0, year = 0)
        }
        fun parseTimePastToString(timePost: Long) : String {
            var res = ""
            val timePast = getTimePastFromNow(timePost)
            if(timePast.day > 30){
                val timePostDate = getTimePastFromNow(timePost)
                res = "${timePostDate.day} thg ${timePostDate.month}, ${timePostDate.year}"
            }
            else {
                if(timePast.day > 0) res = "${timePast.day} ngày"
                else if(timePast.hour > 0) res = "${timePast.hour} giờ"
                else if(timePast.minute > 0) res = "${timePast.minute} phút"
                else if(timePast.second > 15) res = "${timePast.second} giây"
                else res = "Vừa xong"
            }
            return res
        }
    }
}