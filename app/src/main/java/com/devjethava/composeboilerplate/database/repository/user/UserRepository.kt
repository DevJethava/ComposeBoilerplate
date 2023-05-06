package com.devjethava.composeboilerplate.database.repository.user

import com.devjethava.composeboilerplate.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<List<UserEntity>>

    suspend fun insertUser(user: UserEntity)

    suspend fun deleteAllUser()
}