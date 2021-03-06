package com.s32xlevel.foodtracker.util

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

class TimeUtil {
    companion object {
        fun convertTimeString(s: String): String {
            val split = s.split(":").toMutableList()
            for (i in split.indices) {
                if (split[i].toInt() < 10) {
                    split[i] = "0${split[i]}"
                }
            }
            split.add("00")
            return "${split[0]}:${split[1]}:${split[2]}"
        }

        fun <T : Comparable<T>> isBetween(value: T, start: T?, end: T?): Boolean {
            return (start == null || value >= start) && (end == null || value <= end)
        }

        fun convertStringTimeToDateTime(time: String): DateTime = DateTimeFormat.forPattern("HH:mm:ss").parseDateTime(time)
    }
}