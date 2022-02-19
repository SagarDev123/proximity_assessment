package com.assessment.proximity_assessment.adapter.custom

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.assessment.proximity_assessment.utils.CommonUtils
import com.assessment.proximity_assessment.utils.TimeUtils

object CustomTimeAdapter {
    @BindingAdapter("custom_date")
    @JvmStatic
    fun loadCustomDateText(view: TextView, dateString: String) {
        view.text = TimeUtils.updateTimeFormat(dateString)
    }
}