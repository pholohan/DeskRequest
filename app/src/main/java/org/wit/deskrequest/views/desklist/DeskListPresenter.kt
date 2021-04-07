package org.wit.deskrequest.views.desklist

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView

class DeskListPresenter(view: BaseView) : BasePresenter(view), AnkoLogger {

  fun viewMeetConfDetails() {
  }

  fun loadMeetConf() {
    doAsync {
      val rooms = app.rooms.filterMeetConf()
      info("Rooms: $rooms")
      uiThread {
        view?.showRooms(rooms)
      }
    }
  }
}