package com.devjethava.composeboilerplate.network.repository

import com.devjethava.composeboilerplate.network.response.Response
import com.devjethava.composeboilerplate.network.response.UserResponse
import kotlinx.coroutines.flow.Flow

interface RestApiRepository {

    /**
     * Get the list of Users.
     */
    suspend fun getUser(): Flow<Response<UserResponse>>
}