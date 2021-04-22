package org.wit.deskrequest.models.firebase

import android.content.Context
import org.jetbrains.anko.AnkoLogger
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.models.RoomStore
import com.google.firebase.database.*
import org.wit.deskrequest.models.Desk

class RoomsFirestore(val context: Context) : RoomStore, AnkoLogger {

    val rooms = ArrayList<RoomModel>()
    val desks = ArrayList<Desk>()
    lateinit var userId: String
    lateinit var db: DatabaseReference
}