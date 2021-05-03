package org.wit.deskrequest.views.officelist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_room_list.*
import org.wit.deskrequest.R
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.roomlist.RoomAdapter
import org.wit.deskrequest.views.roomlist.RoomListPresenter
import org.wit.deskrequest.views.roomlist.RoomListener

class OfficeListView : BaseView(), RoomListener {

  lateinit var presenter: OfficeListPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_office_list)

    setSupportActionBar(toolbarList)
    super.init(toolbarList, true);

    presenter = initPresenter(OfficeListPresenter(this)) as OfficeListPresenter
    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    presenter.loadOffices()

    val bottomNavigationView =
        findViewById<View>(R.id.bottomNav) as BottomNavigationView

    bottomNavigationView.setOnNavigationItemSelectedListener { item ->
      when (item.itemId) {
        R.id.item_book -> presenter.loadWelcome()
        R.id.item_room_bookings -> presenter.loadRoomBookings()
        R.id.item_bookings -> presenter.loadBookings()
        R.id.item_settings -> presenter.userSettings()
        R.id.item_logout -> presenter.doLogout()
      }
      true
    }

  }

  override fun onRoomClick(room: RoomModel) {
    presenter.viewDeskDetails(room)
  }

  override fun showRooms(rooms: List<RoomModel>) {
    recyclerView.adapter = RoomAdapter(rooms, this)
    recyclerView.adapter?.notifyDataSetChanged()
  }
}