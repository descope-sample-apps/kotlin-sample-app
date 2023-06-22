package com.example.kotlinsampleapp.pages.Welcome

import androidx.compose.runtime.Composable

@Composable
fun WelcomeRoute(
    onNavigateToVerify: (email: String) -> Unit,
) {
    WelcomeScreen(
        onOTPSent = onNavigateToVerify
    )
}
