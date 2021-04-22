package org.wit.deskrequest.models.firebase

import android.content.Context
import com.google.firebase.database.DatabaseReference
import org.jetbrains.anko.AnkoLogger
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.models.RoomStore

class BookingsFirestore(val context: Context) : RoomStore, AnkoLogger {

    val bookings = ArrayList<BookingModel>()
    lateinit var userId: String
    lateinit var db: DatabaseReference

}