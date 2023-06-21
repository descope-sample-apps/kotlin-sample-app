package com.example.kotlinsampleapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.descope.Descope
import com.descope.session.DescopeSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FlowDoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val incomingUri: Uri = intent?.data ?: return // The incoming App link

        // `exchange` is a suspended function.
        // Use whichever scope makes sense in your app or keep the global scope
        GlobalScope.launch(Dispatchers.Main) {
            try {
                // exchange the incoming URI for a session
                val authResponse = Descope.flow.currentRunner?.exchange(incomingUri) ?: throw Exception("Flow is not running")
                val session = DescopeSession(authResponse)
                Descope.sessionManager.manageSession(session)

                // Show the post-authentication screen, for example
                startActivity(Intent(this@FlowDoneActivity, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                })
            } catch (e: Exception) {
                // Handle errors here
            }
            finish() // There's no UI for this Activity, it just handles the logic
        }
    }
}