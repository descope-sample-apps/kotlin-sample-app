package com.example.kotlinsampleapp.pages.LoggedIn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.descope.Descope
import com.descope.session.refreshJwt
import com.descope.types.DescopeUser
import com.example.kotlinsampleapp.pages.SignInSignUpTopAppBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class) // Scaffold is experimental in m3
@Composable
fun LoggedInScreen(onNavUp: () -> Unit, ) {
    val coroutineScope = rememberCoroutineScope()
    val emailState = remember { mutableStateOf<String?>(null) }

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
        return null
    }

    // Example of getting user with /me
    suspend fun getUserEmailMe(): String? {
        val refreshToken: String? = Descope.sessionManager.session?.refreshJwt
        return if (refreshToken != null) {
            Descope.auth.me(refreshToken).email
        } else {
            null
        }
    }
    LaunchedEffect(key1 = Unit) {
        emailState.value = getUserEmailMe()
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