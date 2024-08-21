package com.example.societyapp

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AnnouncementRepository {
    private val db = FirebaseFirestore.getInstance()
    private val announcementsCollection = db.collection("announcements")

    fun getAnnouncements(): Flow<List<Announcement>> = callbackFlow {
        val listener = announcementsCollection.orderBy("timestamp").addSnapshotListener { snapshot, _ ->
            val announcements = snapshot?.documents?.map { it.toObject(Announcement::class.java)!! } ?: emptyList()
            trySend(announcements)
        }
        awaitClose { listener.remove() }
    }
}
