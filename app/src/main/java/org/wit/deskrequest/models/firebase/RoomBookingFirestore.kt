package org.wit.deskrequest.models.firebase

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.deskrequest.models.RoomBookingModel
import org.wit.deskrequest.models.RoomBookingStore

class RoomBookingsFirestore(val context: Context) : RoomBookingStore, AnkoLogger {

  val roombookings = ArrayList<RoomBookingModel>()
  lateinit var userId: String
  lateinit var db: DatabaseReference


  override fun findAll(): MutableList<RoomBookingModel> {
    return roombookings
  }

  override fun create(roombooking: RoomBookingModel) {
    db = FirebaseDatabase.getInstance().reference
    userId = FirebaseAuth.getInstance().currentUser!!.uid
    val key = db.child("users").child(userId).child("bookings").push().key
    key?.let {
      roombooking.fbid = key
      roombookings.add(roombooking)
      db.child("users").child(userId).child("roombookings").child(key).setValue(roombooking)
    }
  }


  override fun update(roombooking: RoomBookingModel) {
    var foundBooking: RoomBookingModel? = roombookings.find { p -> p.fbid == roombooking.fbid }
    if (foundBooking != null) {
      foundBooking.d_duration = roombooking.d_duration
    }
    db.child("users").child(userId).child("roombookings").child(roombooking.fbid).setValue(roombooking)
  }

  override fun delete(roombooking: RoomBookingModel) {
    db.child("users").child(userId).child("roombookings").child(roombooking.fbid).removeValue()
    roombookings.remove(roombooking)
  }

  override fun clear() {
    roombookings.clear()
  }

  fun fetchBookings(roombookingsReady: () -> Unit) {
    val valueEventListener = object : ValueEventListener {
      override fun onCancelled(dataSnapshot: DatabaseError) {
      }
      override fun onDataChange(dataSnapshot: DataSnapshot) {
        info("Room Bookings Data $dataSnapshot")
        dataSnapshot!!.children.mapNotNullTo(roombookings) { it.getValue<RoomBookingModel>(RoomBookingModel::class.java)}
        roombookingsReady()
      }
    }
    userId = FirebaseAuth.getInstance().currentUser!!.uid
    db = FirebaseDatabase.getInstance().reference
    roombookings.clear()
    db.child("users").child(userId).child("roombookings").addListenerForSingleValueEvent(valueEventListener)
  }
}
