package com.devjethava.composeboilerplate.database.repository.user

import com.devjethava.composeboilerplate.database.dao.UserDao
import com.devjethava.composeboilerplate.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {
    override fun getAllUsers(): Flow<List<UserEntity>> {
        return userDao.getAllUsers()
    }

    override suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    override suspend fun deleteAllUser() {
        userDao.deleteAllUser()
    }

}