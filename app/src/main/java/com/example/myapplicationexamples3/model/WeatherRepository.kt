package com.example.myapplicationexamples3.model

import retrofit2.Call

class WeatherRepository(private val geoApi: GeoApi) {

    private val API_KEY = "8a7b1ac803e3d60a05a46660da8dc0ad"

    fun getWeatherForecast(lat: Double, lon: Double): Call<WeatherResponse> {
        return geoApi.getWeatherForecast(lat, lon, API_KEY)
    }
}