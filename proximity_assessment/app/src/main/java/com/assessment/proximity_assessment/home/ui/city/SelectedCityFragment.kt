package com.assessment.proximity_assessment.home.ui.city

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.assessment.proximity_assessment.CitySelectCallback
import com.assessment.proximity_assessment.R
import com.assessment.proximity_assessment.adapter.CityAQIAdapter
import com.assessment.proximity_assessment.databinding.FragmentSelectedCityBinding
import com.assessment.proximity_assessment.room.AQIDatabase
import com.assessment.proximity_assessment.room.entity.AQI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SelectedCityFragment : Fragment(),CitySelectCallback {
    var listOfCity:List<AQI> = ArrayList()
    lateinit var binding: FragmentSelectedCityBinding
    lateinit var  selectedCityViewModel:SelectedCityViewModel
    lateinit var navController: NavController
    var adapter :CityAQIAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        inflater.inflate(R.layout.fragment_selected_city, container, false)
        binding = FragmentSelectedCityBinding.inflate(inflater)
        selectedCityViewModel = ViewModelProvider(this).get(SelectedCityViewModel::class.java)
        selectedCityViewModel.init(requireActivity())
        selectedCityViewModel.aqiLiveData.observe(requireActivity(), Observer {
            listOfCity = it
            if (adapter!=null){
                adapter?.updateContent(listOfCity)
            }
        })
        setRecyclerView()


        return binding.root
    }

    private fun setRecyclerView() {
        if (adapter==null){
            adapter = CityAQIAdapter(listOfCity,this)
            binding.aqiList.layoutManager = LinearLayoutManager(requireActivity())
            binding.aqiList.adapter = adapter
        }else{
            binding.aqiList.layoutManager = LinearLayoutManager(requireActivity())
            binding.aqiList.adapter = adapter
        }
    }

    override fun onSelectCity(name: String) {
        val bundle = bundleOf("name" to name)
        navController?.navigate(R.id.action_selectedCityFragment_to_cityListFragment2,bundle)
    }


}