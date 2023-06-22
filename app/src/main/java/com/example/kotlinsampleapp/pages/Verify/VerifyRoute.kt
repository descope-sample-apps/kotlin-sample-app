package com.example.kotlinsampleapp.pages.Verify

import androidx.compose.runtime.Composable
import com.example.kotlinsampleapp.pages.Welcome.VerifyScreen

@Composable
fun VerifyRoute(
    onNavigateToLoggedIn: (email: String) -> Unit,
    email: String?
) {
   VerifyScreen(
       onSignedIn = onNavigateToLoggedIn,
       email = email
    )
}
