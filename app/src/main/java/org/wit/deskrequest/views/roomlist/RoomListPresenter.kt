package org.wit.deskrequest.views.roomlist

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW

class RoomListPresenter (view: BaseView) : BasePresenter(view){

  fun viewRoomDetails() {
  }

  fun loadRooms() {
    doAsync {
      val rooms = app.rooms.findAll()
      uiThread {
        view?.showRooms(rooms)
      }
    }
  }
}