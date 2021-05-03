package org.wit.deskrequest.views.booking

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.models.Desk
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW

class BookingPresenter(view: BaseView) : BasePresenter(view), AnkoLogger {

  var booking = BookingModel()

  init {
    if (view.intent.hasExtra("booking")) {
      booking = view.intent.extras?.getParcelable<BookingModel>("booking")!!
      //room = view.intent.extras?.getParcelable<RoomModel>("room")!!
      view.showBooking(booking)
    } else {
    }
  }

  fun doUpdateBooking(duration: String) {
    booking.d_duration = duration
    doAsync {
      app.bookings.update(booking)
      uiThread {
        view?.finish()
      }
    }
  }

  fun doCancelBooking(){
    app.bookings.delete(booking)
    view?.finish()
  }

  fun loadWelcome() {
    view?.navigateTo(VIEW.WELCOME)
  }

  fun loadBookings() {
    view?.navigateTo(VIEW.BOOKINGS)
  }

  fun loadRoomBookings() {
    view?.navigateTo(VIEW.ROOMBOOKINGS)
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