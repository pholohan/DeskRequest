package org.wit.deskrequest.views.roomlist

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_book_options.*
import kotlinx.android.synthetic.main.activity_room_list.*
import org.wit.deskrequest.R
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.BaseView

class RoomListView : BaseView(), RoomListener {

  lateinit var presenter: RoomListPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_room_list)

    setSupportActionBar(toolbar)
    super.init(toolbar, true);

    presenter = initPresenter(RoomListPresenter(this)) as RoomListPresenter
    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    presenter.loadRooms()

  }

  override fun onRoomClick(room: RoomModel) {
    presenter.viewRoomDetails()
  }
}