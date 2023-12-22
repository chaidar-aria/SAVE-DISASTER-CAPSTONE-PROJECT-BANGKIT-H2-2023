package com.bangkitcapstone.safedisaster.network.earthquake

import com.bangkitcapstone.safedisaster.model.response.BmkgEarthQuakeResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceEarthQuake {

    @GET("autogempa.json")
    fun getEarthquakeEarthQuake(): Call<BmkgEarthQuakeResponse>
}