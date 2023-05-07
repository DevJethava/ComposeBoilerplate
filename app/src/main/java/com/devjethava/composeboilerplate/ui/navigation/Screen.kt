package com.devjethava.composeboilerplate.ui.navigation

sealed class Screen(val route: String) {
    private companion object {
        const val SPLASH_SCREEN = "splash_screen"
        const val HOME_SCREEN = "home_screen"
        const val MAIN_SCREEN = "main_screen"
        const val DETAIL_SCREEN = "detail_screen"
    }

    object SplashScreen : Screen(SPLASH_SCREEN)
    object HomeScreen : Screen(HOME_SCREEN)
    object MainScreen : Screen(MAIN_SCREEN)
    object DetailScreen : Screen(DETAIL_SCREEN)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}
