package com.example.kotlinsampleapp.pages.Welcome

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WelcomeRoute(
    onNavigateToVerify: (email: String) -> Unit,
) {
    WelcomeScreen(
        onOTPSent = onNavigateToVerify
    )
}
