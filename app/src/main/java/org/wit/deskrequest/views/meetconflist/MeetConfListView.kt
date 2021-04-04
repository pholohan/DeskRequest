package org.wit.deskrequest.views.meetconflist

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_room_list.*
import org.wit.deskrequest.R
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.roomlist.RoomAdapter
import org.wit.deskrequest.views.roomlist.RoomListPresenter
import org.wit.deskrequest.views.roomlist.RoomListener

class MeetConfListView : BaseView(), RoomListener {

  lateinit var presenter: MeetConfListPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_meetconf_list)

    setSupportActionBar(toolbarList)
    super.init(toolbarList, true);

    presenter = initPresenter(MeetConfListPresenter(this)) as MeetConfListPresenter
    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    presenter.loadMeetConf()

  }

  override fun onRoomClick(room: RoomModel) {
    presenter.viewMeetConfDetails()
  }

  override fun showRooms(rooms: List<RoomModel>) {
    recyclerView.adapter = RoomAdapter(rooms, this)
    recyclerView.adapter?.notifyDataSetChanged()
  }
}