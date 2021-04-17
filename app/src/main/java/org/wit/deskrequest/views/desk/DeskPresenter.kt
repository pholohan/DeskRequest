package org.wit.deskrequest.views.desk

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.models.Desk
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView

class DeskPresenter (view: BaseView) : BasePresenter(view){

  var desk = Desk()
  var booking = BookingModel()

  init {
    if (view.intent.hasExtra("desk")) {
      desk = view.intent.extras?.getParcelable<Desk>("desk")!!
      view.showDesk(desk)
    } else {
    }
  }

  fun doAddBooking(deskid: Long, fbid: Long, d_date: String, d_duration: String){
    booking.deskid = deskid
    booking.fbid = fbid
    booking.d_date = d_date
    booking.d_duration = d_duration
    doAsync {
        app.bookings.create(booking)
      uiThread {
        view?.finish()
      }
    }

  }

}