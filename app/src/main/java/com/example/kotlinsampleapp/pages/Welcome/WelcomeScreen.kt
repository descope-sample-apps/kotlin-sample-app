package com.example.kotlinsampleapp.pages.Welcome

import android.content.Context
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
import androidx.compose.runtime.CompositionLocalContext
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.descope.Descope
import com.descope.session.DescopeSession
import com.descope.types.DeliveryMethod
import com.example.kotlinsampleapp.pages.Email
import com.example.kotlinsampleapp.pages.EmailState
import com.example.kotlinsampleapp.pages.EmailStateSaver
import kotlinx.coroutines.launch


@Composable
fun WelcomeScreen(
    onOTPSent: (email: String) -> Unit
) {
    Surface(modifier = Modifier.supportWideScreen()) {
        Column (modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SignUpOrIn(
                onOTPSent = onOTPSent
            )
            StartFlow(
                context = LocalContext.current
            )
        }
    }
}

@Composable
fun StartFlow(context: Context,
              modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "or, sign up/in with a flow",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 64.dp, bottom = 12.dp)
        )
        Button(

            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp, bottom = 1.dp),
            onClick = {
                try {
                    Descope.flow.create(
                        flowUrl = "<your_flow_url>",
                        deepLinkUrl = "<your_deep_link_url>",
                    ).start(context)
                } catch (e: Exception) {
                    Log.e("ERROR", e.stackTraceToString())
                }
            },
            ) {
            Text(text = "Start flow")
        }
    }
}

@Composable
private fun SignUpOrIn(
    onOTPSent: (email: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
        mutableStateOf(EmailState())
    }
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = "Descope Kotlin Sample App",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 64.dp, bottom = 12.dp)
        )

        val onSubmit: () -> Unit = {
            if (emailState.isValid) {
                try {
                    coroutineScope.launch {
                        Descope.otp.signUpOrIn(method = DeliveryMethod.Email, loginId = "allen.zhou101@gmail.com")
                    }
//                    onSignedIn(emailState.text)
                    onOTPSent(emailState.text)
                } catch (e: Exception) {
                    Log.e("ERROR", e.stackTraceToString())
                }

            } else {
                emailState.enableShowErrors()
            }
        }
        Email(emailState = emailState, imeAction = ImeAction.Done, onImeAction = onSubmit)
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
fun WelcomeScreenPreview() {
        WelcomeScreen(
            onOTPSent = {}
        )
}
