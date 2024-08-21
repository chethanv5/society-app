package com.example.societyapp

import android.app.Application
import com.google.firebase.FirebaseApp
import android.util.Log

class SocietyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        Log.d("SocietyApp", "Firebase initialized successfully")
    }
}
