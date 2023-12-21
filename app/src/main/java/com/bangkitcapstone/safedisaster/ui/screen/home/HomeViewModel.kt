package com.bangkitcapstone.safedisaster.ui.screen.home

import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkitcapstone.safedisaster.model.response.BmkgEarthQuakeResponse
import com.bangkitcapstone.safedisaster.network.ApiConfig.getService
import com.google.android.gms.location.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.Locale
import com.bangkitcapstone.safedisaster.notifications.EarthquakeNotificationService

class HomeViewModel: ViewModel() {

    // Data gempa
    private val _dataGempa = MutableStateFlow<List<BmkgEarthQuakeResponse>>(emptyList())
    val dataGempa: StateFlow<List<BmkgEarthQuakeResponse>> = _dataGempa

    // Lokasi pengguna
    private val _locationState = MutableLiveData<Location?>()
    val locationState: LiveData<Location?> = _locationState

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val notificationService = EarthquakeNotificationService()


    fun loadData(context: Context) {
        val apiService = getService()
        val call = apiService.getEarthquake()

        call.enqueue(object : Callback<BmkgEarthQuakeResponse> {
            override fun onResponse(
                call: Call<BmkgEarthQuakeResponse>,
                response: Response<BmkgEarthQuakeResponse>
            ) {
                val earthquakeInfo = response.body()
                if (earthquakeInfo != null) {
                    _dataGempa.value = listOf(earthquakeInfo)
                    val title = "Gempa Terbaru"
                    val message = "Gempa di: ${earthquakeInfo.infogempa?.gempa?.wilayah}, Magnitudo: ${earthquakeInfo.infogempa?.gempa?.magnitude}"

                    notificationService.sendNotification(context, title, message)

                }
            }

            override fun onFailure(call: Call<BmkgEarthQuakeResponse>, t: Throwable) {
                Toast.makeText(null, "Gagal memuat data gempa", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getLocationNewest(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        try {
            fusedLocationClient.lastLocation.addOnCompleteListener { task ->
                if (task.isSuccessful && task.result != null) {
                    _locationState.postValue(task.result)
                } else {
                    Toast.makeText(context, "Tidak dapat menemukan lokasi", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        } catch (e: SecurityException) {
            Toast.makeText(context, "Tidak dapat menemukan lokasi", Toast.LENGTH_SHORT).show()
        }
    }

    fun getLocationName(context: Context, latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses != null) {
                if (addresses.isNotEmpty()) {
                    val address = addresses[0]
                    return address.locality ?: address.getAddressLine(0)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return "Lokasi tidak ditemukan"
    }


}
