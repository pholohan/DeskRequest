package org.wit.deskrequest.views.conf

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.models.Desk
import org.wit.deskrequest.models.RoomBookingModel
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW
import org.wit.deskrequest.views.welcome.WelcomeView

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