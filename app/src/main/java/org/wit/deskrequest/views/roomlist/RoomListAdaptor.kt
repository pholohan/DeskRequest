package org.wit.deskrequest.views.roomlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_room.view.*
import org.wit.deskrequest.R
import org.wit.deskrequest.models.RoomModel

interface RoomListener {
  fun onRoomClick(room: RoomModel)
}

class RoomAdapter constructor(
    private var rooms: List<RoomModel>,
    private val listener: RoomListener
) :
    RecyclerView.Adapter<RoomAdapter.MainHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
    return MainHolder(
        LayoutInflater.from(parent?.context).inflate(
            R.layout.card_room,
            parent,
            false
        )
    )
  }

  override fun onBindViewHolder(holder: MainHolder, position: Int) {
    val room = rooms[holder.adapterPosition]
    holder.bind(room, listener)
  }

  override fun getItemCount(): Int = rooms.size

  class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(room: RoomModel, listener: RoomListener) {
      itemView.roomName.text = room.roomName
      itemView.roomType.text = room.roomType
      itemView.roomLoc.text = room.location
      itemView.roomCap.text = room.capacity
      itemView.setOnClickListener {
        listener.onRoomClick(room)
      }
    }
  }
}