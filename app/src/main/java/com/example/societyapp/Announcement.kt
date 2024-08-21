package com.example.societyapp

import com.google.firebase.Timestamp

data class Announcement(
    val title: String = "",
    val message: String = "",
    val timestamp: Timestamp = Timestamp.now() // Ensure you're using Firestore's Timestamp type
)


