package com.example.kotlinsampleapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlinsampleapp.Destinations.LOGGED_IN_ROUTE
import com.example.kotlinsampleapp.Destinations.VERIFY_ROUTE
import com.example.kotlinsampleapp.Destinations.WELCOME_ROUTE
import com.example.kotlinsampleapp.pages.LoggedIn.LoggedInRoute
import com.example.kotlinsampleapp.pages.Verify.VerifyRoute
import com.example.kotlinsampleapp.pages.Welcome.WelcomeRoute

object Destinations {
    const val WELCOME_ROUTE = "welcome"
    const val LOGGED_IN_ROUTE = "logged_in"
    const val VERIFY_ROUTE = "verify/{email}"
}

@Composable
fun SampleAppNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = WELCOME_ROUTE
    ) {
        composable(WELCOME_ROUTE) {
            WelcomeRoute(
                onNavigateToLoggedIn = {
                    navController.navigate("logged_in")
                },
                onNavigateToVerify = {
                    navController.navigate("verify/$it")
                }
            )
        }
        composable(LOGGED_IN_ROUTE) {
//            val email = it.arguments?.getString("email")
            LoggedInRoute(onNavUp = navController::navigateUp)
        }
        composable(VERIFY_ROUTE) {
            val email = it.arguments?.getString("email")
            VerifyRoute(email = email, onNavigateToLoggedIn = {
                navController.navigate("logged_in")

            })
        }

    }
}