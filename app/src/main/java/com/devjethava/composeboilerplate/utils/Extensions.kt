package com.devjethava.composeboilerplate.utils

import android.annotation.SuppressLint
import androidx.navigation.NavController
import java.text.SimpleDateFormat


fun NavController.navigateWithPopUp(
    nextRoute: String,  // route name where you want to navigate
    currentRoute: String, // route you want from popUpTo.
    isInclusive: Boolean = true,
    isSaveState: Boolean = false
) {
    this.navigate(nextRoute) {
        popUpTo(currentRoute) {
            inclusive = isInclusive // It can be changed to false if you
            // want to keep your fromRoute exclusive
            saveState = isSaveState
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun String.convertDateFormat(
    inputDateFormat: String = Constants.yyyyMMddTHHmmss,
    outputDateFormat: String = Constants.ddMMyyyyHHmm
): String {
    return try {
        val parser = SimpleDateFormat(inputDateFormat)
        val formatter = SimpleDateFormat(outputDateFormat)
        formatter.format(parser.parse(this)!!)
    } catch (E: Exception) {
        ""
    }
}