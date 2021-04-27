package org.wit.deskrequest.views.login

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.toast
import org.wit.deskrequest.models.firebase.BookingsFirestore
import org.wit.deskrequest.models.firebase.RoomsFirestore
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW

class LoginPresenter(view: BaseView) : BasePresenter(view) {

  var auth: FirebaseAuth = FirebaseAuth.getInstance()
  var fireStore: RoomsFirestore? = null
  var bookfireStore: BookingsFirestore? = null

  init {
    if (app.rooms is RoomsFirestore && app.bookings is BookingsFirestore) {
      fireStore = app.rooms as RoomsFirestore
      bookfireStore = app.bookings as BookingsFirestore
    }
  }

  fun doLogin(email: String, password: String) {
    view?.showProgress()
    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(view!!) { task ->
      if (task.isSuccessful) {
        if (fireStore != null && bookfireStore != null) {
          fireStore!!.fetchRooms {
            bookfireStore!!.fetchBookings {
              view?.hideProgress()
              view?.navigateTo(VIEW.WELCOME)
            }
          }
        } else {
          view?.hideProgress()
          view?.navigateTo(VIEW.WELCOME)
        }
      } else {
        view?.hideProgress()
        view?.toast("Sign Up Failed: ${task.exception?.message}")
      }
    }
  }

  fun doSignUp(email: String, password: String) {
    view?.showProgress()
    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(view!!) { task ->
      if (task.isSuccessful) {
        if (fireStore != null && bookfireStore != null) {
          fireStore!!.fetchRooms {
            bookfireStore!!.fetchBookings {
              view?.hideProgress()
              view?.navigateTo(VIEW.WELCOME)
            }
          }
          view?.hideProgress()
          view?.navigateTo(VIEW.WELCOME)
        } else {
          view?.hideProgress()
          view?.toast("Sign Up Failed: ${task.exception?.message}")
        }
      }
    }
  }
}