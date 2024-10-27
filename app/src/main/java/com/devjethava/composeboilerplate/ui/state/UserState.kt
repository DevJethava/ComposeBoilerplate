package com.devjethava.composeboilerplate.ui.state

import com.devjethava.composeboilerplate.network.response.UserResponse

data class UserState(
    val userResponse: UserResponse? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)