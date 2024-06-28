package com.example.myapplicationexamples3.presentation.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationexamples3.R
import com.example.myapplicationexamples3.model.WeatherItem
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class WeatherAdapter(private var weatherList: List<WeatherItem>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    fun updateData(newWeatherList: List<WeatherItem>) {
        weatherList = newWeatherList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather_second, parent, false)
        return WeatherViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherItem = weatherList[position]

        holder.tvMax.text = "${weatherItem.mainDetails.temp_max}°C"
        holder.tvMin.text = "${weatherItem.mainDetails.temp_min}°C"
        holder.tvDateHour.text = SimpleDateFormat("EEE, hh:mm a", Locale.getDefault()).format(Date(weatherItem.dt * 1000))

        val iconUrl = "http://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"
        Picasso.get().load(iconUrl).into(holder.ivIcon)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivIcon: ImageView = itemView.findViewById(R.id.ivIcon)
        val tvDateHour: TextView = itemView.findViewById(R.id.tvDateHour)
        val tvMax: TextView = itemView.findViewById(R.id.tvMax)
        val tvMin: TextView = itemView.findViewById(R.id.tvMin)
    }
}