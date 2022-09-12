package com.example.mobiledevtest.data.network

import com.example.mobiledevtest.models.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/users")
    fun getUsers(
        @Query("page")
        currentPage: Int = 1,
        @Query("amp;per_page")
        pageSize: Int = 10
    ): Call<UserResponse>
}