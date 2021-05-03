package org.wit.deskrequest.views.conf

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.models.Desk
import org.wit.deskrequest.models.RoomBookingModel
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW
import org.wit.deskrequest.views.welcome.WelcomeView
import java.util.*

class ConfPresenter(view: BaseView) : BasePresenter(view), AnkoLogger {

  var room = RoomModel()
  var roombooking = RoomBookingModel()
  var date = WelcomeView.booking_date

  init {
    if (view.intent.hasExtra("room")) {
      room = view.intent.extras?.getParcelable<RoomModel>("room")!!
      info("Room Details to Show: $room")
      view.showRoom(room)
    } else {
    }
  }

  fun doAddRoomBooking(d_duration: String){
    val unique_id = (Date().getTime() / 1000L % Int.MAX_VALUE) as Long
    roombooking.rbookid = unique_id
    roombooking.roomid = room.roomid
    roombooking.roomname = room.roomName
    roombooking.roomtype = room.roomType
    roombooking.d_date = date
    roombooking.d_duration = d_duration
    doAsync {
      app.roombookings.create(roombooking)
      //app.rooms.updateDeskBooked(desk)
      uiThread {
        view?.finish()
      }
    }

  }

  fun loadWelcome() {
    view?.navigateTo(VIEW.WELCOME)
  }

  fun loadBookings() {
    view?.navigateTo(VIEW.BOOKINGS)
  }

  fun userSettings(){
    view?.navigateTo(VIEW.SETTINGS)
  }

  fun doLogout() {
    FirebaseAuth.getInstance().signOut()
    //app.bookings.clear()
    view?.navigateTo(VIEW.LOGIN)
  }
}