package com.assessment.proximity_assessment


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.assessment.proximity_assessment.databinding.ActivityMainBinding
import com.assessment.proximity_assessment.home.MainViewModel
import com.assessment.proximity_assessment.room.AQIDatabase
import com.assessment.proximity_assessment.room.entity.AQI
import com.assessment.proximity_assessment.utils.CommonUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//          setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.init(this)
        mainViewModel.airQualityLiveData.observe(this, Observer {
            // println(it)
            CoroutineScope(Dispatchers.IO).launch {
                for (s in it) {
                    val timeInMilli = System.currentTimeMillis()/1000
                    val isCityExist = AQIDatabase.getDatabase(this@MainActivity).aQiDao().hasCityExist(s.city)
                   // println(isCityExist)
                    AQIDatabase.getDatabase(this@MainActivity).aQiDao()
                        .insertCityAQI(AQI(CommonUtils.generateRandomIdNumbers(),s.aqi,timeInMilli,s.city))
                }
            }
        })
        mainViewModel.airQualityError.observe(this, Observer {
            println("Error message --> $it")
        })
    }


}