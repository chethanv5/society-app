package com.example.societyapp

data class MaintenanceRequest(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val status: String = "", // e.g., "Pending", "In Progress", "Completed"
    val timestamp: Long = 0L
)

