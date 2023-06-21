package com.example.kotlinsampleapp.pages.LoggedIn

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoggedInRoute(
    onNavUp: () -> Unit,
) {
//    val loggedInViewModel: LoggedInViewModel = viewModel(factory = LoggedInViewModelFactory())
    LoggedInScreen(
        onNavUp = onNavUp,
    )
}
