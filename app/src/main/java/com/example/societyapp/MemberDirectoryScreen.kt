package com.example.societyapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun MemberDirectoryScreen(navController: NavHostController) {
    val userRepository = UserRepository()
    val users by userRepository.getUsers().collectAsState(initial = emptyList())
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    // If there's an error, display it
    errorMessage?.let {
        Text(text = "Error: $it", color = MaterialTheme.colorScheme.error)
    }

    // Display the user list if it's not empty
    if (users.isNotEmpty()) {
        LazyColumn {
            items(users) { user ->
                UserCard(user = user)
            }
        }
    } else {
        Text(text = "No users found", modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun UserCard(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = user.name, style = MaterialTheme.typography.titleLarge)
            Text(text = user.email, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
