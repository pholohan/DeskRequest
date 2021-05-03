package org.wit.deskrequest.views.bookinglist

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW

class BookingListPresenter(view: BaseView): BasePresenter(view), AnkoLogger {

  fun viewBooking(booking: BookingModel){
    view?.navigateTo(VIEW.BOOKDETAILS, 0, "booking", booking)
  }

  fun loadBookings() {
    doAsync {
      val bookings = app.bookings.findAll()
      info("Bookings: $bookings")
      uiThread {
        view?.showBookings(bookings)
      }
    }
  }

  fun loadWelcome() {
    view?.navigateTo(VIEW.WELCOME)
  }

  fun loadRoomBookings() {
    view?.navigateTo(VIEW.ROOMBOOKINGS)
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