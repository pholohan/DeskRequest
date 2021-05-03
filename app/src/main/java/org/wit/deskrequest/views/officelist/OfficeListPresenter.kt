package org.wit.deskrequest.views.officelist

import com.google.firebase.auth.FirebaseAuth
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