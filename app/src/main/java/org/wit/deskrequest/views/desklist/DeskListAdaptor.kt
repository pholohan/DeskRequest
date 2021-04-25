package org.wit.deskrequest.views.desklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_desk.view.*
import kotlinx.android.synthetic.main.card_room.view.*
import org.wit.deskrequest.R
import org.wit.deskrequest.models.Desk
import org.wit.deskrequest.models.RoomModel

interface DeskListener {
  fun onDeskClick(desk: Desk)
}

class DeskAdapter constructor(
    private var desks: List<Desk>,
    private val listener: DeskListener
) :
    RecyclerView.Adapter<DeskAdapter.MainHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
    return MainHolder(
        LayoutInflater.from(parent?.context).inflate(
            R.layout.card_desk,
            parent,
            false
        )
    )
  }

  override fun onBindViewHolder(holder: MainHolder, position: Int) {
    val desk = desks[holder.adapterPosition]
    holder.bind(desk, listener)
  }

  override fun getItemCount(): Int = desks.size

  class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(desk: Desk, listener: DeskListener) {
      itemView.deskID.text = ("Desk ID:" + desk.deskid.toString())
      itemView.deskBooked.text = ("Booked:" + desk.deskbooked.toString())
      itemView.setOnClickListener {
        listener.onDeskClick(desk)
      }
    }
  }
}