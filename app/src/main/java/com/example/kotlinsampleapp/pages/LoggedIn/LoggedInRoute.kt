package com.example.kotlinsampleapp.pages.LoggedIn

import androidx.compose.runtime.Composable

@Composable
fun LoggedInRoute(
    onNavUp: () -> Unit,
) {
    LoggedInScreen(
        onNavUp = onNavUp,
    )
}
