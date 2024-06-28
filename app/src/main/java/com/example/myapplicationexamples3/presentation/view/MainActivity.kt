package com.example.myapplicationexamples3.presentation.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationexamples3.R
import com.example.myapplicationexamples3.model.CityInfo
import com.example.myapplicationexamples3.presentation.viewmodel.CityInfoAdapter
import com.example.myapplicationexamples3.presentation.viewmodel.CityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var cityInfoAdapter: CityInfoAdapter
    private val cityViewModel: CityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchView: SearchView = findViewById(R.id.searchView)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        cityInfoAdapter = CityInfoAdapter(emptyList()) { cityInfo: CityInfo ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("cityName", cityInfo.name)
                putExtra("lat", cityInfo.lat)
                putExtra("lon", cityInfo.lon)
            }
            startActivity(intent)
        }
        recyclerView.adapter = cityInfoAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    cityViewModel.fetchCityInfo(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        cityViewModel.cityInfoList.observe(this, Observer { cityInfoList ->
            cityInfoAdapter.updateData(cityInfoList)
        })

        cityViewModel.errorMessage.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }
}