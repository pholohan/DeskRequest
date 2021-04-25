package org.wit.deskrequest.views.desklist

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import org.wit.deskrequest.models.Desk
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW

class DeskListPresenter(view: BaseView) : BasePresenter(view), AnkoLogger {

  var room = RoomModel(desk = arrayListOf())

  fun viewDesk(desk: Desk) {
    view?.navigateTo(VIEW.DESKDETAILS, 0, "desk", desk)
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
      val desks = app.rooms.filterDesks(roomid)!!
      info("Desks: $desks")
      uiThread {
        view?.showDesks(desks)
      }
    }
  }

  fun loadBookings() {
    view?.navigateTo(VIEW.BOOKINGS)
  }

  fun doLogout() {
    FirebaseAuth.getInstance().signOut()
    //app.bookings.clear()
    view?.navigateTo(VIEW.LOGIN)
  }
}
