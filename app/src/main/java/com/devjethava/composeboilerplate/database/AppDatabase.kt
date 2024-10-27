package com.devjethava.composeboilerplate.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devjethava.composeboilerplate.database.AppDatabase.Companion.DATABASE_VERSION
import com.devjethava.composeboilerplate.database.dao.UserDao
import com.devjethava.composeboilerplate.database.entity.UserEntity


@Database(entities = [UserEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        /*DATABASE CONSTANTS*/
        const val DATABASE_NAME = "COMPOSE_APP_DB"
        const val DATABASE_VERSION = 2
    }
}