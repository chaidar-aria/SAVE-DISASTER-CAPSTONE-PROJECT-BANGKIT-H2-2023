package com.bangkitcapstone.safedisaster.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkitcapstone.safedisaster.R
import com.bangkitcapstone.safedisaster.model.response.BmkgEarthQuakeResponse
import com.bangkitcapstone.safedisaster.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EarthquakeNotificationService: ViewModel(){

    private val _dataGempaNotif = MutableLiveData<List<BmkgEarthQuakeResponse>>()
    val dataGempaNotif: MutableLiveData<List<BmkgEarthQuakeResponse>> = _dataGempaNotif

    val apiService = ApiConfig.getService()
    val call = apiService.getEarthquake()

    fun loadDataNotif(context: Context) {
        call.enqueue(object : Callback<BmkgEarthQuakeResponse> {
            override fun onResponse(
                call: Call<BmkgEarthQuakeResponse>,
                response: Response<BmkgEarthQuakeResponse>
            ) {
                val earthquakeInfo = response.body()
                if (earthquakeInfo != null) {
                    _dataGempaNotif.value = listOf(earthquakeInfo)
                    val title = "Gempa Terbaru"
                    val message = "Gempa di: ${earthquakeInfo.infogempa?.gempa?.wilayah}, Magnitudo: ${earthquakeInfo.infogempa?.gempa?.magnitude}"

                    sendNotification(context, title, message)

                }
            }

            override fun onFailure(call: Call<BmkgEarthQuakeResponse>, t: Throwable) {
                Toast.makeText(null, "Gagal memuat data gempa", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun sendNotification(context: Context, title: String, message: String) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.application_logo)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = builder.build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = "SAFE DISASTER CHANNEL"
    }

}

