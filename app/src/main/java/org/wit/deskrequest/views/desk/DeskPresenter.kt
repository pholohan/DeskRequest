package org.wit.deskrequest.views.desk

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.models.Desk
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW
import org.wit.deskrequest.views.welcome.WelcomeView
import java.util.*

class DeskPresenter(view: BaseView) : BasePresenter(view), AnkoLogger{

  var desk = Desk()
  var room = RoomModel()
  var booking = BookingModel()
  var date = WelcomeView.booking_date

  init {
    if (view.intent.hasExtra("desk")) {
      desk = view.intent.extras?.getParcelable<Desk>("desk")!!
      //room = view.intent.extras?.getParcelable<RoomModel>("room")!!
      info("Room: $room")
      info("Desk: $desk")
      view.showDesk(desk)
    } else {
    }
  }

  /*fun doUpdateDeskBooked(deskid:Long) {
      desk = app.rooms.findDeskById(roomid,deskid)!!
    doAsync {
      app.rooms.updateDeskBooked(desk)
      uiThread {
        view?.finish()
      }
    }
  }*/

  fun doAddBooking(deskid: Long, d_duration: String){
    val unique_id = (Date().getTime() / 1000L % Int.MAX_VALUE) as Long
    booking.dbookid = unique_id
    booking.deskid = deskid
    booking.d_date = date
    booking.d_duration = d_duration
    doAsync {
        app.bookings.create(booking)
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

  fun doLogout() {
    FirebaseAuth.getInstance().signOut()
    //app.bookings.clear()
    view?.navigateTo(VIEW.LOGIN)
  }

}