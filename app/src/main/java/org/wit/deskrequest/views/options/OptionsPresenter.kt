package org.wit.deskrequest.views.options

import com.google.firebase.auth.FirebaseAuth
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW

class OptionsPresenter(view: BaseView) : BasePresenter(view){

  fun showMeetConf() {
    view?.navigateTo(VIEW.MEETCONF)
  }

  fun showOffice() {
    view?.navigateTo(VIEW.OFFICE)
  }

  fun showRoomsList() {
    view?.navigateTo(VIEW.LIST)
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