package com.example.myapplicationexamples3.model

import com.google.gson.annotations.SerializedName


data class WeatherResponse(
    val list: List<WeatherItem>,
    val city: City
)

data class WeatherItem(
    val dt: Long,
    @SerializedName("main")
    val mainDetails: MainDetails,
    val weather: List<Weather>,
    val dt_txt: String
)

data class MainDetails(
    val temp: Float,
    val temp_min: Float,
    val temp_max: Float
)

data class Weather(
    val description: String,
    val icon: String
)

data class City(
    val name: String
)