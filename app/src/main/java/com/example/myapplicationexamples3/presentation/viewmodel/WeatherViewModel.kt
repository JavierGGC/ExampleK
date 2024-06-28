package com.example.myapplicationexamples3.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationexamples3.model.WeatherRepository
import com.example.myapplicationexamples3.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _weatherResponse = MutableLiveData<WeatherResponse>()
    val weatherResponse: LiveData<WeatherResponse>
        get() = _weatherResponse

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun fetchWeatherForecast(lat: Double, lon: Double) {
        val call = weatherRepository.getWeatherForecast(lat, lon)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    _weatherResponse.value = response.body()
                } else {
                    _errorMessage.value = "Failed to retrieve data"
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                _errorMessage.value = "Request failed: ${t.message}"
            }
        })
    }
}