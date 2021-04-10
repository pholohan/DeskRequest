package org.wit.deskrequest.views.desklist

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView

class DeskListPresenter(view: BaseView) : BasePresenter(view), AnkoLogger {

  var room = RoomModel(desk = arrayListOf())

  fun viewMeetConfDetails() {
  }

  init {
    if (view.intent.hasExtra("desk_list")) {
      room = view.intent.extras?.getParcelable<RoomModel>("desk_list")!!
      loadDesks(room)
    } else {
    }
  }

  fun loadDesks(room: RoomModel) {
    doAsync {
      val roomid = room.roomid
      val desks = app.rooms.filterDesks(roomid)
      info("Desks: $desks")
      uiThread {
        view?.showDesks(desks)
      }
    }
  }
}