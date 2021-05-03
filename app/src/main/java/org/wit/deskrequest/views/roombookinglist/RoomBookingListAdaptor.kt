package org.wit.deskrequest.views.roombookinglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_booking.view.*
import kotlinx.android.synthetic.main.card_booking.view.dbookid
import kotlinx.android.synthetic.main.card_room_booking.view.*
import org.wit.deskrequest.R
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.models.RoomBookingModel


interface RoomBookingListener {
    fun onRoomBookingClick(roombooking: RoomBookingModel)
  }

class RoomBookingListAdaptor constructor(
      private var roombookings: List<RoomBookingModel>,
      private val listener: RoomBookingListener
  ) :
      RecyclerView.Adapter<RoomBookingListAdaptor.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
      return MainHolder(
          LayoutInflater.from(parent?.context).inflate(
              R.layout.card_room_booking,
              parent,
              false
          )
      )
    }

  override fun onBindViewHolder(holder: MainHolder, position: Int) {
      val roombooking = roombookings[holder.adapterPosition]
      holder.bind(roombooking, listener)
    }

    override fun getItemCount(): Int = roombookings.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

      fun bind(roombooking: RoomBookingModel, listener: RoomBookingListener) {
        itemView.rbookid.text = ("Booking ID: " + roombooking.rbookid)
        itemView.roombookName.text = ("Room Booked: " + roombooking.roomname)
        itemView.roombookType.text = ("Room Type: " + roombooking.roomtype)
        itemView.room_d_date.text = ("Date Booked: " + roombooking.d_date)
        itemView.room_d_duration.text = ("Duration: " + roombooking.d_duration)
        itemView.setOnClickListener {
          listener.onRoomBookingClick(roombooking)
        }
      }
    }
}