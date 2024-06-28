package com.example.myapplicationexamples3.presentation.view

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationexamples3.R
import com.example.myapplicationexamples3.presentation.viewmodel.WeatherAdapter
import com.example.myapplicationexamples3.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {

    private val weatherViewModel: WeatherViewModel by viewModel()
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val cityName = intent.getStringExtra("cityName") ?: ""
        val lat = intent.getDoubleExtra("lat", 0.0)
        val lon = intent.getDoubleExtra("lon", 0.0)

        findViewById<TextView>(R.id.tvCityDetail).text = cityName

        val recyclerView: RecyclerView = findViewById(R.id.rvWeather)
        recyclerView.layoutManager = LinearLayoutManager(this)
        weatherAdapter = WeatherAdapter(emptyList())
        recyclerView.adapter = weatherAdapter

        weatherViewModel.fetchWeatherForecast(lat, lon)

        weatherViewModel.weatherResponse.observe(this, { weatherResponse ->
            weatherAdapter.updateData(weatherResponse.list)
        })

        weatherViewModel.errorMessage.observe(this, { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }
}