package org.wit.deskrequest.views.welcome

import com.google.firebase.auth.FirebaseAuth
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW

class WelcomePresenter (view: BaseView) : BasePresenter(view)  {

  fun showOptions() {
    view?.navigateTo(VIEW.OPTIONS)
  }

  fun loadBookings() {
    view?.navigateTo(VIEW.BOOKINGS)
  }
  fun loadWelcome() {
    view?.navigateTo(VIEW.WELCOME)
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