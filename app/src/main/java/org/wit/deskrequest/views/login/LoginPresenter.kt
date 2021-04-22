package org.wit.deskrequest.views.login

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.toast
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW

class LoginPresenter(view: BaseView) : BasePresenter(view) {

  var auth: FirebaseAuth = FirebaseAuth.getInstance()
  //var fireStore: HillfortFireStore? = null

  init {
    if (app.hillforts is HillfortFireStore) {
      fireStore = app.hillforts as HillfortFireStore
    }
  }

  fun doLogin(email: String, password: String) {
    view?.showProgress()
    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(view!!) { task ->
      if (task.isSuccessful) {
        if(fireStore != null) {
          fireStore!!.fetchHillforts {
            view?.hideProgress()
            view?.navigateTo(VIEW.LIST)
          }
        }else{
          view?.hideProgress()
          view?.navigateTo(VIEW.LIST)
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
        fireStore!!.fetchHillforts {
          view?.hideProgress()
          view?.navigateTo(VIEW.LIST)
        }
        view?.hideProgress()
        view?.navigateTo(VIEW.LIST)
      } else {
        view?.hideProgress()
        view?.toast("Sign Up Failed: ${task.exception?.message}")
      }
    }
  }
}
