package com.example.societyapp.ui.theme

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.societyapp.AddAnnouncementScreen
import com.example.societyapp.AnnouncementsScreen
import com.example.societyapp.EventScreen
import com.example.societyapp.ForumScreen
import com.example.societyapp.HomeScreen
import com.example.societyapp.MaintenanceRequestsScreen
import com.example.societyapp.MemberDirectoryScreen
import com.example.societyapp.ProfileScreen
import com.example.societyapp.SignUpScreen

@Composable
fun NavGraph(startDestination: String = "login") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("members") { MemberDirectoryScreen(navController) }
        composable("announcements") { AnnouncementsScreen(navController) }
        composable("add_announcement") { AddAnnouncementScreen(navController) }
        composable("events") { EventScreen() }
        composable("forum") { ForumScreen() }
        composable("maintenance_requests") { MaintenanceRequestsScreen() }
    }
}

