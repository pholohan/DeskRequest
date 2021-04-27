package org.wit.deskrequest.views.roomlist

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW

class RoomListPresenter (view: BaseView) : BasePresenter(view), AnkoLogger{

  fun viewRoomDetails() {
  }

  fun loadRooms() {
    doAsync {
      val rooms = app.rooms.findAll()
      info("Rooms: $rooms")
      uiThread {
        view?.showRooms(rooms)
      }
    }
  }

  fun loadWelcome() {
    view?.navigateTo(VIEW.WELCOME)
  }

  fun loadBookings() {
    view?.navigateTo(VIEW.BOOKINGS)
  }

  fun userSettings(){
    view?.navigateTo(VIEW.SETTINGS)
  }

  fun doLogout() {
    FirebaseAuth.getInstance().signOut()
    //app.bookings.clear()
    view?.navigateTo(VIEW.LOGIN)
  }
}