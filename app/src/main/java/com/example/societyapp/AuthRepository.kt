package com.example.societyapp

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    fun signUp(email: String, password: String, name: String, callback: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: ""
                    val user = mapOf("name" to name, "email" to email)

                    firestore.collection("users").document(userId)
                        .set(user)
                        .addOnCompleteListener { firestoreTask ->
                            if (firestoreTask.isSuccessful) {
                                callback(true, null)
                            } else {
                                callback(false, "Failed to write user data to Firestore: ${firestoreTask.exception?.message}")
                            }
                        }
                } else {
                    callback(false, "Authentication failed: ${task.exception?.message}")
                }
            }
    }
}


