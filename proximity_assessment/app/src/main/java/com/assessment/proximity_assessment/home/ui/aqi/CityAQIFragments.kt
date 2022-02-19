package com.assessment.proximity_assessment.home.ui.aqi

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.assessment.proximity_assessment.databinding.FragmentCityAQIBinding
import com.assessment.proximity_assessment.room.entity.AQI
import com.assessment.proximity_assessment.utils.TimeUtils
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate


class CityAQIFragments : Fragment() {

    lateinit var  binding: FragmentCityAQIBinding
    lateinit var navController: NavController
    lateinit var cityAQIViewModel: CityAQIViewModel

    // variable for our bar data.
    var barData: LineData? = null

    // variable for our bar data set.
    var barDataSet: LineDataSet? = null

    // array list for storing entries.
    private var barEntriesArrayList: ArrayList<Entry>?  = ArrayList<Entry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        //println("name -->  ${arguments?.get("name")}")
    }

    override fun onResume() {
        super.onResume()
        cityAQIViewModel.init(requireActivity(), arguments?.get("name") as String?)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //inflater.inflate(R.layout.fragment_city_a_q_i_fragmens, container, false)
        binding = FragmentCityAQIBinding.inflate(inflater)
        cityAQIViewModel = ViewModelProvider(this).get(CityAQIViewModel::class.java)
        cityAQIViewModel.aqiData.observe(requireActivity(), Observer {
            populateDateToGraphs(it)
        })
        return binding.root
    }

    fun initGraphData(){

        // creating a new bar data set.

        // creating a new bar data set.
        barDataSet = LineDataSet(barEntriesArrayList, "Time")

        // creating a new bar data and
        // passing our bar data set.

        // creating a new bar data and
        // passing our bar data set.
        barData = LineData(barDataSet)

        // below line is to set data
        // to our bar chart.
        binding.chart1.data = barData
        // adding color to our bar data set.
        barDataSet!!.setColors(*ColorTemplate.MATERIAL_COLORS)
        // setting text color.
        barDataSet!!.valueTextColor = Color.BLACK
        // setting text size
        barDataSet!!.valueTextSize = 16f
        binding.chart1.description?.isEnabled=false
    }

    private fun populateDateToGraphs(list: List<AQI>) {
        if (list != null) {
            barEntriesArrayList?.clear()
            var count = 0
            for (aqi in list){
//                println("aqi float --> ${aqi.aqi.toFloat()} count float --> ${count.toFloat()} " +
//                        "city --> ${aqi.city} time --> ${aqi.time}")
                barEntriesArrayList?.add(Entry(count.toFloat(), aqi.aqi.toFloat()))
                count++
            }
            initGraphData()
        }
    }


}