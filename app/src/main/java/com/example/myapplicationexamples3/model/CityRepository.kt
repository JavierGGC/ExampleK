package com.example.myapplicationexamples3.model

import retrofit2.Call

class CityRepository(private val geoApi: GeoApi) {

    private val API_KEY = "8a7b1ac803e3d60a05a46660da8dc0ad"

    fun getCityInfo(query: String): Call<List<CityInfo>> {
        return geoApi.getCityInfo(query, 5, API_KEY)
    }
}