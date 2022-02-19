package com.assessment.proximity_assessment.utils

import java.util.*

object CommonUtils {
    fun generateRandomIdNumbers(): Int {
        val min = 20
        val max = 80
        return Random().nextInt(max - min + 1) + min
    }

    fun updateCustomAQI(s: String): String? {
        val number = s.toDouble()
        val number3digits:Double = Math.round(number * 1000.0) / 1000.0
        val number2digits:Double = Math.round(number3digits * 100.0) / 100.0
        val solution:Double = Math.round(number2digits * 10.0) / 10.0
        return solution.toString()
    }
}