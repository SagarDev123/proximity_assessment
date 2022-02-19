package com.assessment.proximity_assessment.home.ui.city

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assessment.proximity_assessment.room.AQIDatabase
import com.assessment.proximity_assessment.room.entity.AQI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectedCityViewModel:ViewModel() {
    val aqiLiveData = MutableLiveData<List<AQI>>()
    fun init(selectedCityFragment: FragmentActivity) {

       CoroutineScope(Dispatchers.IO).launch {
              val list = AQIDatabase.getDatabase(selectedCityFragment).aQiDao().getAllCityList()
           filterData(list,selectedCityFragment)
       }
    }

    private fun filterData(list: List<AQI>, selectedCityFragment: FragmentActivity) {
        val arrayListOfCity = ArrayList<String>()
        for (item in list){
            arrayListOfCity.add(item.city)
        }

        val hashSet = HashSet<String>()
        hashSet.addAll(arrayListOfCity)
        arrayListOfCity.clear()
        arrayListOfCity.addAll(hashSet)

        val listOfCityWithMax = ArrayList<AQI>()
        for (item in arrayListOfCity){
            val hList =  AQIDatabase.getDatabase(selectedCityFragment).aQiDao().getHighestValue(item)
            //println("hList $hList")
            listOfCityWithMax.add(hList)
        }

        aqiLiveData.postValue(listOfCityWithMax)


       // val invitedPeople: List<AQI> = people.filter { it.nickname == "bob" || it.nickname == "emily" }

    }
}