package com.bangkitcapstone.safedisaster.ui.screen.home

import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkitcapstone.safedisaster.model.response.BmkgEarthQuakeResponse
import com.bangkitcapstone.safedisaster.model.response.OpenWeatherResponse
import com.bangkitcapstone.safedisaster.network.earthquake.ApiConfigEarthQuake.getServiceEarthQuake
import com.bangkitcapstone.safedisaster.network.weather.ApiConfigWeather.getServiceWeather
import com.google.android.gms.location.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.Locale

class HomeViewModel : ViewModel() {

    // Data gempa
    private val _dataGempa = MutableStateFlow<List<BmkgEarthQuakeResponse>>(emptyList())
    val dataGempa: StateFlow<List<BmkgEarthQuakeResponse>> = _dataGempa

    private val _dataWeather = MutableStateFlow<List<OpenWeatherResponse>>(emptyList())
    val dataWeather: StateFlow<List<OpenWeatherResponse>> = _dataWeather

    // Lokasi pengguna
    private val _locationState = MutableLiveData<Location?>()
    val locationState: LiveData<Location?> = _locationState

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var apiKey: String = "cafccc7bf17f3e9ee98f94da97610491"


    fun loadData(context: Context) {
        val apiService = getServiceEarthQuake()
        val call = apiService.getEarthquakeEarthQuake()

        call.enqueue(object : Callback<BmkgEarthQuakeResponse> {
            override fun onResponse(
                call: Call<BmkgEarthQuakeResponse>,
                response: Response<BmkgEarthQuakeResponse>
            ) {
                val earthquakeInfo = response.body()
                if (earthquakeInfo != null) {
                    _dataGempa.value = listOf(earthquakeInfo)
                    val title = "Gempa Terbaru"
                    val message =
                        "Gempa di: ${earthquakeInfo.infogempa?.gempa?.wilayah}, Magnitudo: ${earthquakeInfo.infogempa?.gempa?.magnitude}"

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

    fun weatherData(context: Context) {

        val latitude = locationState.value?.latitude ?: return
        val longitude = locationState.value?.longitude ?: return

        val apiServiceWeather = getServiceWeather()
        val callWeather = apiServiceWeather.getCurrentWeather(
            latitude,
            longitude,
            apiKey,
            "metric",
            "id"
        )

        callWeather.enqueue(object : Callback<OpenWeatherResponse> {
            override fun onResponse(
                call: Call<OpenWeatherResponse>,
                response: Response<OpenWeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val weatherInfo = response.body()
                    if (weatherInfo != null) {
                        _dataWeather.value = listOf(weatherInfo)
                        val title = "Cuaca Terbaru"
                        val message =
                            "Cuaca di: ${weatherInfo.name}, Suhu: ${weatherInfo.main?.temp}°F, Kelembapan: ${weatherInfo.main?.humidity}%"
                        Log.d("Weather", message)
                    }

                }
            }

            override fun onFailure(call: Call<OpenWeatherResponse>, t: Throwable) {
                // Tangani kegagalan
            }
        })

    }


}
