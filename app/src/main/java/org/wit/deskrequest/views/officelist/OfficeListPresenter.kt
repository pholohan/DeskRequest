package org.wit.deskrequest.views.officelist

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW

class OfficeListPresenter (view: BaseView) : BasePresenter(view), AnkoLogger {

  fun viewDeskDetails(room: RoomModel) {
    view?.navigateTo(VIEW.DESK, 0, "desk_list", room)
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