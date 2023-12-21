package com.bangkitcapstone.safedisaster.preferences

import android.content.Context
import android.content.SharedPreferences

class UserPreference (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences("sessions", Context.MODE_PRIVATE)

    companion object {
        const val USER_EMAIL_KEY = "user_email"
    }

    fun saveUserEmail(email: String) {
        val editor = prefs.edit()
        editor.putString(USER_EMAIL_KEY, email)
        editor.apply()
    }

    fun getUserEmail(): String? {
        return prefs.getString(USER_EMAIL_KEY, null)
    }

    fun clearSession() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }

}