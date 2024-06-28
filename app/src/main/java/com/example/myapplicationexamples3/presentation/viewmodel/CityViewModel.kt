package com.example.myapplicationexamples3.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationexamples3.model.CityInfo
import com.example.myapplicationexamples3.model.CityRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityViewModel (private val cityRepository: CityRepository) : ViewModel() {

    private val _cityInfoList = MutableLiveData<List<CityInfo>>()
    val cityInfoList: LiveData<List<CityInfo>>
        get() = _cityInfoList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun fetchCityInfo(query: String) {
        val call = cityRepository.getCityInfo(query)
        call.enqueue(object : Callback<List<CityInfo>> {
            override fun onResponse(call: Call<List<CityInfo>>, response: Response<List<CityInfo>>) {
                if (response.isSuccessful) {
                    _cityInfoList.value = response.body()
                } else {
                    _errorMessage.value = "Failed to retrieve data"
                }
            }

            override fun onFailure(call: Call<List<CityInfo>>, t: Throwable) {
                _errorMessage.value = "Request failed: ${t.message}"
            }
        })
    }
}