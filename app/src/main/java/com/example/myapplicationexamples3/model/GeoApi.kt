package com.example.myapplicationexamples3.model


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoApi {
    @GET("geo/1.0/direct")
    fun getCityInfo(
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("appid") apiKey: String
    ): Call<List<CityInfo>>

    @GET("data/2.5/forecast")
    fun getWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String
    ): Call<WeatherResponse>
}