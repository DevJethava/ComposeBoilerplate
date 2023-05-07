package com.devjethava.composeboilerplate.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.devjethava.composeboilerplate.ui.navigation.ComposeBoilerplateNavHost
import com.devjethava.composeboilerplate.ui.theme.ComposeBoilerplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBoilerplateTheme {
                ComposeBoilerplateNavHost()
            }
        }
    }
}