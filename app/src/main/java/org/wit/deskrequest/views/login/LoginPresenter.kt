package org.wit.deskrequest.views.login

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.deskrequest.models.firebase.BookingsFirestore
import org.wit.deskrequest.models.firebase.RoomBookingsFirestore
import org.wit.deskrequest.models.firebase.RoomsFirestore
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW

class LoginPresenter(view: BaseView) : BasePresenter(view), AnkoLogger {

  var auth: FirebaseAuth = FirebaseAuth.getInstance()
  var fireStore: RoomsFirestore? = null
  var bookfireStore: BookingsFirestore? = null
  var roombookfireStore: RoomBookingsFirestore? = null


  init {
    if (app.rooms is RoomsFirestore && app.bookings is BookingsFirestore && app.roombookings is RoomBookingsFirestore) {
      fireStore = app.rooms as RoomsFirestore
      bookfireStore = app.bookings as BookingsFirestore
      roombookfireStore = app.roombookings as RoomBookingsFirestore
    }
  }

  fun doLogin(email: String, password: String) {
    view?.showProgress()
    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(view!!) { task ->
      if (task.isSuccessful) {
        if (fireStore != null) {
          fireStore!!.fetchRooms {}
        }
        if (bookfireStore != null) {
          bookfireStore!!.fetchBookings {}
        }
        if (roombookfireStore != null) {
          roombookfireStore!!.fetchRoomBookings {}
        }
        view?.hideProgress()
        view?.navigateTo(VIEW.WELCOME)
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
          if (fireStore != null) {
            fireStore!!.fetchRooms {}
          }
          if (bookfireStore != null) {
            bookfireStore!!.fetchBookings {}
          }
          if (roombookfireStore != null) {
            roombookfireStore!!.fetchRoomBookings {}
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


