package com.example.societyapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Announcement
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to the Society App",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            ),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))

        HomeScreenButton(
            icon = Icons.Default.Person,
            text = "Profile",
            onClick = { navController.navigate("profile") }
        )

        HomeScreenButton(
            icon = Icons.Default.Group,
            text = "Member Directory",
            onClick = { navController.navigate("members") }
        )

        HomeScreenButton(
            icon = Icons.AutoMirrored.Filled.Announcement,
            text = "Announcements",
            onClick = { navController.navigate("announcements") }
        )

        HomeScreenButton(
            icon = Icons.Default.Event,
            text = "Events",
            onClick = { navController.navigate("events") }
        )

        HomeScreenButton(
            icon = Icons.Default.Forum,
            text = "Forum",
            onClick = { navController.navigate("forum") }
        )

        HomeScreenButton(
            icon = Icons.Default.Build,
            text = "Maintenance Requests",
            onClick = { navController.navigate("maintenance_requests") }
        )
    }
}

@Composable
fun HomeScreenButton(icon: ImageVector, text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
    ) {
        Icon(imageVector = icon, contentDescription = text, tint = Color.White)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, style = TextStyle(color = Color.White, fontSize = 18.sp))
    }
}
