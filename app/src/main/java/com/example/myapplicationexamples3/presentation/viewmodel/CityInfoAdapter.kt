package com.example.myapplicationexamples3.presentation.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationexamples3.R
import com.example.myapplicationexamples3.model.CityInfo

class CityInfoAdapter(
    private var cityInfoList: List<CityInfo>,
    private val onCityClickListener: (CityInfo) -> Unit
) : RecyclerView.Adapter<CityInfoAdapter.CityInfoViewHolder>() {

    fun updateData(newCityInfoList: List<CityInfo>) {
        cityInfoList = newCityInfoList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityInfoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather, parent, false)
        return CityInfoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityInfoViewHolder, position: Int) {
        val cityInfo = cityInfoList[position]
        holder.cityName.text = cityInfo.name
        holder.cityState.text = cityInfo.state
        holder.itemView.setOnClickListener { onCityClickListener(cityInfo) }
    }

    override fun getItemCount(): Int = cityInfoList.size

    class CityInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName: TextView = itemView.findViewById(R.id.tvCity)
        val cityState: TextView = itemView.findViewById(R.id.tvState)
    }
}
