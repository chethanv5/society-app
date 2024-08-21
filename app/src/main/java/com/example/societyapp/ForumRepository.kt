package com.example.societyapp

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ForumRepository {
    private val db = FirebaseFirestore.getInstance()
    private val postsCollection = db.collection("forum_posts")

    fun getPosts(): Flow<List<ForumPost>> = callbackFlow {
        val listener = postsCollection.orderBy("timestamp").addSnapshotListener { snapshot, _ ->
            val posts = snapshot?.documents?.map { it.toObject(ForumPost::class.java)!! } ?: emptyList()
            trySend(posts)
        }
        awaitClose { listener.remove() }
    }
}

