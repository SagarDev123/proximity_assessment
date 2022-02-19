package com.assessment.proximity_assessment.home.ui.aqi

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assessment.proximity_assessment.room.AQIDatabase
import com.assessment.proximity_assessment.room.entity.AQI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityAQIViewModel:ViewModel() {

    val aqiData = MutableLiveData<List<AQI>>()

    fun init(requireActivity: FragmentActivity, city: String?) {
        CoroutineScope(Dispatchers.IO).launch {
          val list = city?.let {
              AQIDatabase.getDatabase(requireActivity).aQiDao().getCityAQIByCityName(
                  it
              )
          }

            println("filtered $list")
            aqiData.postValue(list!!)
        }
    }
}