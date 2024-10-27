package com.devjethava.composeboilerplate.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var name: String = "",
    var gender: String = "",
    var imageURL: String = "",
    var email: String = "",
    var dob: String = "",
    var phoneNumber: String = "",
)