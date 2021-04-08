package org.wit.deskrequest.views.desklist

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_room_list.*
import org.wit.deskrequest.R
import org.wit.deskrequest.models.Desk
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.roomlist.RoomAdapter
import org.wit.deskrequest.views.roomlist.RoomListener

class DeskListView : BaseView(), DeskListener {

  lateinit var presenter: DeskListPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_desk_list)

    setSupportActionBar(toolbarList)
    super.init(toolbarList, true);

    presenter = initPresenter(DeskListPresenter(this)) as DeskListPresenter
    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    presenter.loadDesks()

  }

  override fun onDeskClick(desk: Desk) {
    presenter.viewMeetConfDetails()
  }

  override fun showDesks(desks: List<Desk>) {
    recyclerView.adapter = DeskAdapter(desks, this)
    recyclerView.adapter?.notifyDataSetChanged()
  }
}