package com.bangkitcapstone.safedisaster.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkitcapstone.safedisaster.model.UserModel
import com.bangkitcapstone.safedisaster.network.ApiConfig
import com.bangkitcapstone.safedisaster.preferences.UserPreference
import com.chaidar.storyappsubmis.backend.response.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val pref: UserPreference) : ViewModel() {

    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> = _login

    private fun saveSession(user: UserModel) {
        viewModelScope.launch {
            ApiConfig.setToken(user.tokenAuth)
            pref.saveSession(user)
        }
    }

    fun login(email: String, password: String) {
        val loginResult = ApiConfig.getService().login(email, password)

        loginResult.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val tokenAuth = responseBody?.loginResult?.token as String
                    _login.value = true
                    saveSession(UserModel(tokenAuth, true))
                    Log.d("Ini-Token:", "Login: ${tokenAuth}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}