package com.devjethava.composeboilerplate.network

import com.devjethava.composeboilerplate.network.response.UserResponse
import retrofit2.http.GET

interface ApiService {
    /**
     * Get the list of Users.
     */
    @GET("api/")
    suspend fun getUser(): UserResponse
}