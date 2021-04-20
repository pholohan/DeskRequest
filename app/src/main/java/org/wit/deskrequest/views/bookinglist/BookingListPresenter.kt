package org.wit.deskrequest.views.bookinglist

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView

class BookingListPresenter(view: BaseView): BasePresenter(view), AnkoLogger {

  fun viewBooking(booking: BookingModel){

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
}