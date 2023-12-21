package com.bangkitcapstone.safedisaster.network

import com.bangkitcapstone.safedisaster.model.response.BmkgEarthQuakeResponse
import com.bangkitcapstone.safedisaster.model.response.EarthQuakeResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("autogempa.json")
    fun getEarthquake(): Call<BmkgEarthQuakeResponse>
}