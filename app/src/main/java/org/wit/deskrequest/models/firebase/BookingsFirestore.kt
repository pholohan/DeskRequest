package org.wit.deskrequest.models.firebase

import android.content.Context
import com.google.firebase.database.DatabaseReference
import com.google.gson.Gson
import org.jetbrains.anko.AnkoLogger
import org.wit.deskrequest.helpers.exists
import org.wit.deskrequest.helpers.read
import org.wit.deskrequest.helpers.write
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.models.BookingStore
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.models.RoomStore
import org.wit.deskrequest.models.json.JSON_BOOKING_FILE
import org.wit.deskrequest.models.json.generateRandomBookingId
import org.wit.deskrequest.models.json.gsonBookingBuilder
import org.wit.deskrequest.models.json.listTypeBooking

class BookingsFirestore(val context: Context) : BookingStore, AnkoLogger {

    val bookings = ArrayList<BookingModel>()
    lateinit var userId: String
    lateinit var db: DatabaseReference


    override fun findAll(): MutableList<BookingModel> {
        return bookings
    }

    override fun create(booking: BookingModel) {
        booking.dbookid = generateRandomBookingId()
        bookings.add(booking)
    }


    override fun update(booking: BookingModel) {
        var foundBooking: BookingModel? = bookings.find { p -> p.dbookid == booking.dbookid }
        if (foundBooking != null) {
            foundBooking.d_date = booking.d_date
            foundBooking.d_duration = booking.d_duration
        }
    }

    override fun delete(booking: BookingModel) {
        var foundBooking: BookingModel? = bookings.find { p -> p.dbookid == booking.dbookid }
        if (foundBooking != null) {
            bookings.remove(foundBooking)
        }
    }
}
