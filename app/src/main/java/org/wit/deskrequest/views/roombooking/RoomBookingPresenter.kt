package org.wit.deskrequest.views.roombooking

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.models.RoomBookingModel
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW

class RoomBookingPresenter (view: BaseView) : BasePresenter(view), AnkoLogger {

    var roombooking = RoomBookingModel()

    init {
      if (view.intent.hasExtra("roombooking")) {
        roombooking = view.intent.extras?.getParcelable<RoomBookingModel>("roombooking")!!
        //room = view.intent.extras?.getParcelable<RoomModel>("room")!!
        view.showRoomBooking(roombooking)
      } else {
      }
    }

    fun doUpdateBooking(duration: String) {
      roombooking.d_duration = duration
      doAsync {
        app.roombookings.update(roombooking)
        uiThread {
          view?.finish()
        }
      }
    }

    fun doCancelBooking(){
      app.roombookings.delete(roombooking)
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
