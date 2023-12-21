package com.bangkitcapstone.safedisaster.ui.screen.home

import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkitcapstone.safedisaster.model.response.BmkgEarthQuakeResponse
import com.bangkitcapstone.safedisaster.network.ApiConfig.getService
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.Locale

class HomeViewModel: ViewModel() {

    // Data gempa
    private val _dataGempa = MutableStateFlow<List<BmkgEarthQuakeResponse>>(emptyList())
    val dataGempa: StateFlow<List<BmkgEarthQuakeResponse>> = _dataGempa

    // Lokasi pengguna
    private val _locationState = MutableLiveData<Location?>()
    val locationState: LiveData<Location?> = _locationState

    private lateinit var fusedLocationClient: FusedLocationProviderClient



    init {
        // Inisialisasi data gempa
        loadData()

    }

    private fun loadData() {
        val apiService = getService()
        val call = apiService.getEarthquake()

        call.enqueue(object : Callback<BmkgEarthQuakeResponse> {
            override fun onResponse(
                call: Call<BmkgEarthQuakeResponse>,
                response: Response<BmkgEarthQuakeResponse>
            ) {
                // Proses respons
            }

            override fun onFailure(call: Call<BmkgEarthQuakeResponse>, t: Throwable) {
                // Proses kegagalan
            }
        })
    }

    fun getLocationNewest(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        try {
            fusedLocationClient.lastLocation.addOnCompleteListener(OnCompleteListener { task ->
                if (task.isSuccessful && task.result != null) {
                    _locationState.postValue(task.result)
                } else {
                    // Log error atau tangani kasus ketika lokasi tidak ditemukan
                }
            })
        } catch (e: SecurityException) {
            // Log atau tangani kesalahan izin
        }
    }

    fun getLocationName(context: Context, latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses != null) {
                if (addresses.isNotEmpty()) {
                    val address = addresses[0]
                    // Ambil detail lokasi yang Anda inginkan, misalnya kota atau alamat lengkap
                    return address.locality ?: address.getAddressLine(0)
                }
            }
        } catch (e: IOException) {
            // Log atau tangani kesalahan
        }
        return "Lokasi tidak ditemukan"


    }


}
