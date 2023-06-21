package com.example.kotlinsampleapp.pages.Welcome

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WelcomeRoute(
    onNavigateToLoggedIn: (email: String) -> Unit,
    onNavigateToVerify: (email: String) -> Unit,
) {
//    val welcomeViewModel: WelcomeViewModel = viewModel(factory = WelcomeViewModelFactory())
    WelcomeScreen(
        onSignedIn = onNavigateToLoggedIn,
        onOTPSent = onNavigateToVerify
    )
}
