package com.devjethava.composeboilerplate.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devjethava.composeboilerplate.ui.theme.ComposeBoilerplateTheme
import com.devjethava.composeboilerplate.viewmodel.MainViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBoilerplateTheme {
                val viewModel: MainViewModel = hiltViewModel()
                val userResponse = viewModel.userResponse.collectAsState().value
                LaunchedEffect(key1 = true) {
//                    viewModel.getUserFromApi()
                }
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Button(onClick = {
                        viewModel.getUserFromApi()
                    }) {
                        Text(text = "Click Me")
                    }
                    userResponse.userResponse?.let { response ->
                        val data = response.results.first()
//                        viewModel.addUserToDatabase(data)
                        Log.e("MainActivity", Gson().toJson(data))
                        Text(text = data.email ?: "Email not found", Modifier.padding(8.dp))
                    }
                }
            }
//            }
        }
    }
}