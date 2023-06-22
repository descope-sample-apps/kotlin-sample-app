package com.example.kotlinsampleapp.pages.LoggedIn

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoggedInRoute(
    onNavUp: () -> Unit,
) {
    LoggedInScreen(
        onNavUp = onNavUp,
    )
}
