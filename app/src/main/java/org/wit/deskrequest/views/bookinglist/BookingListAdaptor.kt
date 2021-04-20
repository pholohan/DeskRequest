package org.wit.deskrequest.views.bookinglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_booking.view.*
import kotlinx.android.synthetic.main.card_desk.view.*
import kotlinx.android.synthetic.main.card_room.view.*
import org.wit.deskrequest.R
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.models.Desk
import org.wit.deskrequest.models.RoomModel

interface BookingListener {
  fun onBookingClick(booking: BookingModel)
}

class BookingListAdaptor constructor(
    private var bookings: List<BookingModel>,
    private val listener: BookingListener
) :
    RecyclerView.Adapter<BookingListAdaptor.MainHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
    return MainHolder(
        LayoutInflater.from(parent?.context).inflate(
            R.layout.card_booking,
            parent,
            false
        )
    )
  }

  override fun onBindViewHolder(holder: MainHolder, position: Int) {
    val booking = bookings[holder.adapterPosition]
    holder.bind(booking, listener)
  }

  override fun getItemCount(): Int = bookings.size

  class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(booking: BookingModel, listener: BookingListener) {
      itemView.dbookid.text = ("Booking ID:" + booking.dbookid)
      itemView.deskid.text = ("Booked:" + booking.deskid)
      itemView.fbid.text = ("User: " + booking.fbid)
      itemView.d_date.text = ("Date Booked" + booking.d_date)
      itemView.d_duration.text = ("Duration" + booking.d_duration)
      itemView.setOnClickListener {
        listener.onBookingClick(booking)
      }
    }
  }
}