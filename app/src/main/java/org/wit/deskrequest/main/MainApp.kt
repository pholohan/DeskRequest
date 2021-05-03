package org.wit.deskrequest.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.deskrequest.models.BookingStore
import org.wit.deskrequest.models.RoomBookingStore

import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.models.RoomStore
import org.wit.deskrequest.models.firebase.BookingsFirestore
import org.wit.deskrequest.models.firebase.RoomBookingsFirestore
import org.wit.deskrequest.models.firebase.RoomsFirestore
import org.wit.deskrequest.models.json.BookingJSONStore
import org.wit.deskrequest.models.json.RoomJSONStore

class MainApp : Application(), AnkoLogger {

    //val rooms = ArrayList<RoomModel>()
    //val rooms = RoomMemStore()
    lateinit var rooms: RoomStore
    lateinit var bookings: BookingStore
    lateinit var roombookings: RoomBookingStore
    //lateinit var users: UserStore
    //lateinit var loggedinuser: LoggedInUserMemStore

    override fun onCreate() {
        super.onCreate()
        //rooms = RoomJSONStore(applicationContext)
        //bookings = BookingJSONStore(applicationContext)
        //users = UserMemStore()
        rooms = RoomsFirestore(applicationContext)
        bookings = BookingsFirestore(applicationContext)
        roombookings = RoomBookingsFirestore(applicationContext)
        //users = UserJSONStore(applicationContext)
        info("Desk Request App started")
    }
}