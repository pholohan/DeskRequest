package org.wit.deskrequest.views.officelist

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView

class OfficeListPresenter (view: BaseView) : BasePresenter(view), AnkoLogger {

  fun viewOfficeDetails() {
  }

  fun loadOffices() {
    doAsync {
      val rooms = app.rooms.filterOffice()
      info("Rooms: $rooms")
      uiThread {
        view?.showRooms(rooms)
      }
    }
  }
}