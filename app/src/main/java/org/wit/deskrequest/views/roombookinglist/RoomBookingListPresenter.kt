package org.wit.deskrequest.views.roombookinglist

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.models.RoomBookingModel
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW

class RoomBookingListPresenter(view: BaseView): BasePresenter(view), AnkoLogger {

  fun viewRoomBooking(roombooking: RoomBookingModel){
    view?.navigateTo(VIEW.ROOMBOOKDETAILS, 0, "roombooking", roombooking)
  }

  fun loadRoomBookings() {
    doAsync {
      val roombookings = app.roombookings.findAll()
      info("Room Bookings: $roombookings")
      uiThread {
        view?.showRoomBookings(roombookings)
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
    app.bookings.clear()
    view?.navigateTo(VIEW.LOGIN)
  }
}