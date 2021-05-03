package org.wit.deskrequest.views.roombookinglist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_room_list.*
import org.wit.deskrequest.R
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.models.RoomBookingModel
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.bookinglist.BookingListAdaptor
import org.wit.deskrequest.views.bookinglist.BookingListPresenter

class RoomBookingListView: BaseView(), RoomBookingListener {

  lateinit var presenter: RoomBookingListPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_roombooking_list)

    setSupportActionBar(toolbarList)
    super.init(toolbarList, true);

    presenter = initPresenter(RoomBookingListPresenter(this)) as RoomBookingListPresenter
    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    presenter.loadRoomBookings()

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

  override fun onRoomBookingClick(roombooking: RoomBookingModel) {
    presenter.viewRoomBooking(roombooking)
  }

  override fun showRoomBookings(roombookings: List<RoomBookingModel>) {
    recyclerView.adapter = RoomBookingListAdaptor(roombookings, this)
    recyclerView.adapter?.notifyDataSetChanged()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    presenter.loadRoomBookings()
    super.onActivityResult(requestCode, resultCode, data)
  }
}