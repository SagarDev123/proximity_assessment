package com.assessment.proximity_assessment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.assessment.proximity_assessment.CitySelectCallback
import com.assessment.proximity_assessment.R
import com.assessment.proximity_assessment.databinding.ItemAqiValuesBinding
import com.assessment.proximity_assessment.model.AQIModel
import com.assessment.proximity_assessment.room.entity.AQI

class CityAQIAdapter(var list:List<AQI>,var listener:CitySelectCallback):RecyclerView.Adapter<CityAQIAdapter.CityAQIVH>() {
  class CityAQIVH(val itemAqiValuesBinding: ItemAqiValuesBinding):RecyclerView.ViewHolder(itemAqiValuesBinding.root){

  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityAQIVH {
        val itemDataBinding: ItemAqiValuesBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_aqi_values, parent, false
        )
        return CityAQIVH(itemDataBinding)
    }

    override fun onBindViewHolder(holder: CityAQIVH, position: Int) {
        holder.itemAqiValuesBinding.parentLayout.setOnClickListener {
            listener.onSelectCity(list[position].city)
        }
       holder.itemAqiValuesBinding.aqiModel = AQIModel(list[position].aqi.toString(),list[position].city,list[position].time.toString())
    }

    override fun getItemCount(): Int {
       return list.size
    }

    fun updateContent(listOfCity: List<AQI>?) {
        if (listOfCity != null) {
            list = listOfCity
            notifyDataSetChanged()
        }
    }
}