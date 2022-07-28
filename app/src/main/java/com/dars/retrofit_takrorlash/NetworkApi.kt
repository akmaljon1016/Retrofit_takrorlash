package com.dars.retrofit_takrorlash

import com.dars.retrofit_takrorlash.model.UserItem
import retrofit2.Call
import retrofit2.http.GET

interface NetworkApi {
    @GET("users")
    fun getUser(): Call<List<UserItem>>
}