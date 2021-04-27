package org.wit.deskrequest.models.json

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.info
import org.wit.deskrequest.helpers.exists
import org.wit.deskrequest.helpers.read
import org.wit.deskrequest.helpers.write
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.models.BookingStore
import org.wit.deskrequest.models.RoomModel
import java.util.*

val JSON_BOOKING_FILE = "bookings.json"
val gsonBookingBuilder = GsonBuilder().setPrettyPrinting().create()
val listTypeBooking = object : TypeToken<ArrayList<BookingModel>>() {}.type

fun generateRandomBookingId(): Long {
  return Random().nextLong()
}

class BookingJSONStore: BookingStore {


  val context: Context
  var bookings = mutableListOf<BookingModel>()

  constructor (context: Context) {
    this.context = context
    if (exists(context, JSON_BOOKING_FILE)) {
      deserialize()
    }
  }

  override fun findAll(): MutableList<BookingModel> {
    return bookings
  }

  override fun create(booking: BookingModel) {
    booking.dbookid = generateRandomBookingId()
    bookings.add(booking)
    serialize()
  }


  override fun update(booking: BookingModel) {
    var foundBooking: BookingModel? = bookings.find { p -> p.dbookid == booking.dbookid }
    if (foundBooking != null) {
      foundBooking.d_date = booking.d_date
      foundBooking.d_duration = booking.d_duration
      serialize()
    }
  }

  override fun delete(booking: BookingModel) {
    var foundBooking: BookingModel? = bookings.find { p -> p.dbookid == booking.dbookid }
    if (foundBooking != null) {
      bookings.remove(foundBooking)
      serialize()
    }
  }

  private fun serialize() {
    val jsonString = gsonBookingBuilder.toJson(bookings, listTypeBooking)
    write(context, JSON_BOOKING_FILE, jsonString)
  }

  private fun deserialize() {
    val jsonString = read(context, JSON_BOOKING_FILE)
    bookings = Gson().fromJson(jsonString, listTypeBooking)
  }

  override fun clear() {
    bookings.clear()
  }

}