package com.assessment.proximity_assessment.adapter.custom

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.assessment.proximity_assessment.utils.CommonUtils
import com.assessment.proximity_assessment.utils.TimeUtils

object AQIValueCustom {
    @BindingAdapter("custom_aqi")
    @JvmStatic
    fun loadCustomDateText(view: TextView, aqiString: String) {
        view.text = CommonUtils.updateCustomAQI(aqiString)
        val aqiValue = aqiString.toDouble()
        if (aqiValue>0 && aqiValue<51){
            view.setTextColor(Color.parseColor("#55a84f"))
        }else if (aqiValue>50 && aqiValue<101){
            view.setTextColor(Color.parseColor("#a3c853"))
        }else if (aqiValue>100 && aqiValue<201){
            view.setTextColor(Color.parseColor("#fff832"))
        }else if (aqiValue>200 && aqiValue<301){
            view.setTextColor(Color.parseColor("#f29c32"))
        }else if (aqiValue>300 && aqiValue<401){
            view.setTextColor(Color.parseColor("#e93f33"))
        }else if (aqiValue>400 && aqiValue<501){
            view.setTextColor(Color.parseColor("#af2d24"))
        }
    }
}