package org.wit.deskrequest.views.desk

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.models.Desk
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.welcome.WelcomeView

class DeskPresenter (view: BaseView) : BasePresenter(view), AnkoLogger{

  var desk = Desk()
  var booking = BookingModel()
  var date = WelcomeView.booking_date

  init {
    if (view.intent.hasExtra("desk")) {
      desk = view.intent.extras?.getParcelable<Desk>("desk")!!
      info("Desk Intent")
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

  fun doAddBooking(deskid: Long, fbid: Long, d_duration: String){
    booking.deskid = deskid
    booking.fbid = fbid
    booking.d_date = date
    booking.d_duration = d_duration
    doAsync {
        app.bookings.create(booking)
        //app.rooms.updateDeskBooked(desk)
      info("Desk Boolean to be udpated: $desk")
      uiThread {
        view?.finish()
      }
    }

  }

}