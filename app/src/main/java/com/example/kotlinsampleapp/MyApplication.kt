package com.example.kotlinsampleapp

import android.app.Application
import com.descope.Descope
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Descope.projectId="<your_project_id>"
    }
}