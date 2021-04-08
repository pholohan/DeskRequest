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

  fun loadDesks() {
    doAsync {
      val desks = app.rooms.findAllDesks()
      info("Desks: $desks")
      uiThread {
        view?.showDesks(desks)
      }
    }
  }
}