package com.devjethava.composeboilerplate.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devjethava.composeboilerplate.database.AppDatabase
import com.devjethava.composeboilerplate.database.entity.UserEntity
import com.devjethava.composeboilerplate.network.repository.RestApiRepository
import com.devjethava.composeboilerplate.network.response.Response
import com.devjethava.composeboilerplate.network.response.UserData
import com.devjethava.composeboilerplate.ui.state.UserState
import com.devjethava.composeboilerplate.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val restApiRepository: RestApiRepository,
    private val database: AppDatabase
) : ViewModel() {
    private val _userResponse = MutableStateFlow(UserState())
    val userResponse = _userResponse.asStateFlow()

    fun getUserFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            _userResponse.update {
                UserState(userResponse = null, isLoading = false, error = "")
            }
            restApiRepository.getUser().collectLatest { res ->
                when (res) {
                    is Response.Loading -> _userResponse.update {
                        it.copy(
                            isLoading = true,
                            error = ""
                        )
                    }

                    is Response.Error -> _userResponse.update {
                        Log.e("ViewModel", res.message.toString())
                        it.copy(
                            isLoading = false,
                            error = res.message ?: "An unknown error occurred"
                        )
                    }

                    is Response.Success -> _userResponse.update {
                        if (res.data == null) {
                            it.copy(
                                isLoading = false,
                                error = res.message ?: "Sorry can't fetch User right now."
                            )
                        } else {
                            it.copy(
                                userResponse = res.data,
                                isLoading = false,
                                error = ""
                            )
                        }
                    }
                }
            }
        }
    }

    fun addUserToDatabase(userdata: UserData): UserEntity {
        val userEntity = UserEntity()
        userEntity.email = userdata.email ?: ""
        userEntity.gender = userdata.gender ?: ""
        userEntity.name =
            (userdata.name?.title + " " + userdata.name?.first + " " + userdata.name?.last) + ""
        userEntity.phoneNumber = userdata.phone ?: ""
        userEntity.dob = userdata.dob?.date ?: ""
        userEntity.imageURL = userdata.picture?.large ?: Constants.PLACEHOLDER_IMAGE_URL

        viewModelScope.launch(Dispatchers.IO) {
            database.userDao.insertUser(userEntity)
        }
        return userEntity
    }
}