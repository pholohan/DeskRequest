package org.wit.deskrequest.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.deskrequest.models.*

import org.wit.deskrequest.models.firebase.BookingsFirestore
import org.wit.deskrequest.models.firebase.RoomBookingsFirestore
import org.wit.deskrequest.models.firebase.RoomsFirestore

class MainApp : Application(), AnkoLogger {

    lateinit var rooms: RoomStore
    lateinit var bookings: BookingStore
    lateinit var roombookings: RoomBookingStore

    override fun onCreate() {
        super.onCreate()
        rooms = RoomsFirestore(applicationContext)
        bookings = BookingsFirestore(applicationContext)
        roombookings = RoomBookingsFirestore(applicationContext)
        info("Desk Request App started")
    }
}