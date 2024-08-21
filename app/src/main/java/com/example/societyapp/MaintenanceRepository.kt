package com.example.societyapp

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class MaintenanceRepository {
    private val db = FirebaseFirestore.getInstance()
    private val requestsCollection = db.collection("maintenance_requests")

    fun getRequests(): Flow<List<MaintenanceRequest>> = callbackFlow {
        val listener = requestsCollection.orderBy("timestamp").addSnapshotListener { snapshot, _ ->
            val requests = snapshot?.documents?.map { it.toObject(MaintenanceRequest::class.java)!! } ?: emptyList()
            trySend(requests)
        }
        awaitClose { listener.remove() }
    }
}
