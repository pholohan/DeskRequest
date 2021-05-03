package org.wit.deskrequest.views.meetconflist

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW

class MeetConfListPresenter(view: BaseView) : BasePresenter(view), AnkoLogger {

  fun viewMeetDetails(room: RoomModel) {
    view?.navigateTo(VIEW.MEETDETAILS, 0, "room", room)
  }

  fun viewConfDetails(room: RoomModel) {
    view?.navigateTo(VIEW.CONFDETAILS, 0, "room", room)
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

  fun loadWelcome() {
    view?.navigateTo(VIEW.WELCOME)
  }

  fun loadBookings() {
    view?.navigateTo(VIEW.BOOKINGS)
  }

  fun loadRoomBookings() {
    view?.navigateTo(VIEW.ROOMBOOKINGS)
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