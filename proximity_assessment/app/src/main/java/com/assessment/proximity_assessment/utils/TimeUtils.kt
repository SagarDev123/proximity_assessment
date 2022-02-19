package com.assessment.proximity_assessment.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {
    fun updateTimeFormat( time :String):String{
//        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val outPutFormat = SimpleDateFormat("HH:mm:ss")
        return  outPutFormat.format(Date(time.toLong()))
    }


}