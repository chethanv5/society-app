package com.example.societyapp

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class EventRepository {
    private val db = FirebaseFirestore.getInstance()
    private val eventsCollection = db.collection("events")

    fun getEvents(): Flow<List<Event>> = callbackFlow {
        val listener = eventsCollection.orderBy("date").addSnapshotListener { snapshot, _ ->
            val events = snapshot?.documents?.map { it.toObject(Event::class.java)!! } ?: emptyList()
            trySend(events)
        }
        awaitClose { listener.remove() }
    }
}
