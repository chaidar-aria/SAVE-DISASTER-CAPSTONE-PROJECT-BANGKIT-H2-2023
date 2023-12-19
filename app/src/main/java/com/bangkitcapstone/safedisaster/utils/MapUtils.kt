package com.bangkitcapstone.safedisaster.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun checkIfPermissionGranted(context: Context, permission: String): Boolean {
    return (ContextCompat.checkSelfPermission(context, permission)
            == PackageManager.PERMISSION_GRANTED)
}

fun shouldShowPermissionRationale(context: Context, permission: String): Boolean {

    val activity = context as Activity?
    if (activity == null)
        Log.d("Maps Util", "Activity is null")

    return ActivityCompat.shouldShowRequestPermissionRationale(
        activity!!,
        permission
    )
}