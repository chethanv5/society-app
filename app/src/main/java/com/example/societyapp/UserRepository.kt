package com.example.societyapp

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")

    fun getUsers(): Flow<List<User>> = callbackFlow {
        val listener = usersCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)  // Close the flow with an error
                return@addSnapshotListener
            }

            try {
                val users = snapshot?.documents?.mapNotNull { it.toObject(User::class.java) } ?: emptyList()
                trySend(users).isSuccess
            } catch (e: Exception) {
                close(e)  // Close the flow with an exception
            }
        }
        awaitClose { listener.remove() }
    }
}
