package org.wit.deskrequest.models.firebase

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.gson.Gson
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
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
        db = FirebaseDatabase.getInstance().reference
        userId = FirebaseAuth.getInstance().currentUser!!.uid
        val key = db.child("users").child(userId).child("bookings").push().key
        key?.let {
            booking.fbid = key
            bookings.add(booking)
            db.child("users").child(userId).child("bookings").child(key).setValue(booking)
        }
    }


    override fun update(booking: BookingModel) {
        var foundBooking: BookingModel? = bookings.find { p -> p.fbid == booking.fbid }
        if (foundBooking != null) {
            foundBooking.d_duration = booking.d_duration
        }
        db.child("users").child(userId).child("bookings").child(booking.fbid).setValue(booking)
    }

    override fun delete(booking: BookingModel) {
        db.child("users").child(userId).child("bookings").child(booking.fbid).removeValue()
        bookings.remove(booking)
    }

    override fun clear() {
        bookings.clear()
    }

    fun fetchBookings(bookingsReady: () -> Unit) {
        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                info("Bookings Data $dataSnapshot")
                dataSnapshot!!.children.mapNotNullTo(bookings) { it.getValue<BookingModel>(BookingModel::class.java)}
                bookingsReady()
            }
        }
        userId = FirebaseAuth.getInstance().currentUser!!.uid
        db = FirebaseDatabase.getInstance().reference
        bookings.clear()
        db.child("users").child(userId).child("bookings").addListenerForSingleValueEvent(valueEventListener)
    }
}
