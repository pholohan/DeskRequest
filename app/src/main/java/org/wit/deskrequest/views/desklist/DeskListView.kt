package org.wit.deskrequest.views.desklist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
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

    val bottomNavigationView =
        findViewById<View>(R.id.bottomNav) as BottomNavigationView

    bottomNavigationView.setOnNavigationItemSelectedListener { item ->
      when (item.itemId) {
        R.id.item_book -> presenter.loadWelcome()
        //R.id.item_room -> presenter.doShowHillfortsMap()
        R.id.item_bookings -> presenter.loadBookings()
        //R.id.item_settings -> presenter.userSettings()
        R.id.item_logout -> presenter.doLogout()
      }
      true
    }
  }

  override fun onDeskClick(desk: Desk) {
    presenter.viewDesk(desk)
  }

  override fun showDesks(desks: List<Desk>) {
    recyclerView.adapter = DeskAdapter(desks, this)
    recyclerView.adapter?.notifyDataSetChanged()
  }

  //override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
  //  presenter.loadDesks(room)
  //  super.onActivityResult(requestCode, resultCode, data)
  //}
}