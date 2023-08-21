package com.example.kotlinsampleapp

import android.app.Application
import com.descope.Descope
import com.descope.sdk.DescopeConfig

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Descope.config = DescopeConfig("", "http://localhost:8000")
    }
}
