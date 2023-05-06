package com.devjethava.composeboilerplate.network.repository

import com.devjethava.composeboilerplate.network.ApiService
import com.devjethava.composeboilerplate.network.response.Response
import com.devjethava.composeboilerplate.network.response.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RestApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : RestApiRepository {

    /**
     * Get the list of Users.
     */
    override suspend fun getUser(): Flow<Response<UserResponse>> = flow {
        emit(Response.Loading())
        try {
            val response = apiService.getUser()
            emit(Response.Success(response))

        } catch (e: HttpException) {
            emit(Response.Error(message = "Oops, something went wrong"))
        } catch (e: IOException) {
            emit(Response.Error(message = "Couldn't reach server check your internet connection"))
        } catch (e: Exception) {
            emit(Response.Error(message = "Oops, something went wrong"))
        }
    }
}