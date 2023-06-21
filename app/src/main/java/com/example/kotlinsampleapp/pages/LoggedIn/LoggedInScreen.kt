package com.example.kotlinsampleapp.pages.LoggedIn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.descope.Descope
import com.descope.types.DescopeUser
import com.example.kotlinsampleapp.pages.SignInSignUpTopAppBar

@OptIn(ExperimentalMaterial3Api::class) // Scaffold is experimental in m3
@Composable
fun LoggedInScreen(onNavUp: () -> Unit, ) {
    // we create a DescopeSession object that represents an authenticated user session
    fun getUser(): DescopeUser? {
        val session = Descope.sessionManager.session
        if (session !== null) {
            return session.user;
        }
        return null
    }
    fun getUserEmail(): String? {
        val user = getUser();
        if (user !== null) {
            return user.email;
        }
        return ""
    }

    Scaffold(
        topBar = {
            SignInSignUpTopAppBar(
                topAppBarText = "Logged in",
                onNavUp = onNavUp
            )
        },
        content = {contentPadding ->
            LazyColumn(modifier = Modifier.fillMaxWidth(), contentPadding = contentPadding) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        if (getUserEmail() !== null) {
                            Text(
                                text = getUserEmail() ?: ""
                            )
                        } else {
                            Text(text = "No email found")
                        }
                    }
                }
            }
        }
    )
}