package org.wit.deskrequest.views.settings

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.toast
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW

class SettingsPresenter (view: BaseView) : BasePresenter(view) {

  val user = FirebaseAuth.getInstance().currentUser

  fun doUpdateEmail(email: String) {
    user!!.updateEmail(email).addOnCompleteListener { task ->
      if (task.isSuccessful) {
        view?.toast("User Email updated.")
        view?.finish()
      }
    }
  }

  fun doUpdatePassword(password: String)
  {
    user!!.updatePassword(password).addOnCompleteListener { task ->
      if (task.isSuccessful) {
        view?.toast("User Password updated.")
        view?.finish()
      }
    }
  }

  fun loadWelcome() {
    view?.navigateTo(VIEW.WELCOME)
  }

  fun userSettings(){
    view?.navigateTo(VIEW.SETTINGS)
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