package com.bangkitcapstone.safedisaster.network

import com.bangkitcapstone.safedisaster.network.response.AddStoryResponse
import com.bangkitcapstone.safedisaster.network.response.AllStoryResponse
import com.chaidar.storyappsubmis.backend.response.GetStoryResponse
import com.chaidar.storyappsubmis.backend.response.LoginResponse
import com.chaidar.storyappsubmis.backend.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    @Headers("No-Authentication: true")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    @Headers("No-Authentication: true")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("stories")
    suspend fun getStories(
        @Query("page") page: Int? = 1,
        @Query("size") size: Int? = 20,
        @Query("location") location: Int = 0
    ): AllStoryResponse


    @GET("stories/{userId}")
    fun getDetailStory(
        @Path("userId") userId: String
    ): Call<GetStoryResponse>

    @Multipart
    @POST("stories")
    fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): Call<AddStoryResponse>

    @GET("stories")
    fun getStoriesWithLocation(
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
        @Query("location") location : Int = 1,
    ): Call<AllStoryResponse>
}