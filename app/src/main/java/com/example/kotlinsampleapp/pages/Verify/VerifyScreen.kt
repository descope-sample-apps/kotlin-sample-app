package com.example.kotlinsampleapp.pages.Welcome

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kotlinsampleapp.util.supportWideScreen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.descope.Descope
import com.descope.session.DescopeSession
import com.descope.types.DeliveryMethod
import com.example.kotlinsampleapp.pages.Email
import com.example.kotlinsampleapp.pages.EmailState
import com.example.kotlinsampleapp.pages.EmailStateSaver
import com.example.kotlinsampleapp.pages.OTP
import com.example.kotlinsampleapp.pages.OTPState
import com.example.kotlinsampleapp.pages.OTPStateSaver
import kotlinx.coroutines.launch


@Composable
fun VerifyScreen(
    onSignedIn: (email: String) -> Unit,
    email: String?
) {
    Surface(modifier = Modifier.supportWideScreen()) {
        Column (modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())) {
            Verify(
                onSignedIn = onSignedIn,
                email = email
            )
        }
    }
}

@Composable
private fun Verify(
    onSignedIn: (email: String) -> Unit,
    email: String?,
    modifier: Modifier = Modifier
) {
    val otpState by rememberSaveable(stateSaver = OTPStateSaver) {
        mutableStateOf(OTPState())
    }
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = "Enter your OTP",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 64.dp, bottom = 12.dp)
        )
        val onSubmit: () -> Unit = {
            if (otpState.isValid) {
                coroutineScope.launch {
                    if (email !== null) {
                       try {
                           // if the user entered the right code the authentication is successful
                           val authResponse = Descope.otp.verify(
                               method = DeliveryMethod.Email,
                               loginId = email,
                               code = otpState.text
                           )

                           // we create a DescopeSession object that represents an authenticated user session
                           val session = DescopeSession(authResponse)

                           // the session manager automatically takes care of persisting the session
                           // and refreshing it as needed
                           Descope.sessionManager.manageSession(session)

                           onSignedIn(email)
                       } catch (e: Exception) {
                           Log.e("ERROR", e.stackTraceToString())
                       }
                    }
                }
            } else {
                otpState.enableShowErrors()
            }
        }

        OTP(otpState = otpState, imeAction = ImeAction.Done, onImeAction = onSubmit)
        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp, bottom = 3.dp)
        ) {
            Text(
                text = "Continue",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Preview
@Composable
fun VerifyScreenPreview() {
    VerifyScreen(
        onSignedIn = {},
        email = ""
    )
}
