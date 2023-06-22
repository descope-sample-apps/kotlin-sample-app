package com.example.kotlinsampleapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.descope.Descope
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

    fun getStartDestination(): String {
        // check if we have a valid session from a previous launch and that it hasn't expired yet
        return if (Descope.sessionManager.session?.refreshToken?.isExpired != false) {
              WELCOME_ROUTE
         } else {
              LOGGED_IN_ROUTE
         }
    }

    NavHost(
        navController = navController,
        startDestination = getStartDestination()
    ) {
        composable(WELCOME_ROUTE) {
            WelcomeRoute(
                onNavigateToVerify = {
                    navController.navigate("verify/$it")
                }
            )
        }
        composable(LOGGED_IN_ROUTE) {
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