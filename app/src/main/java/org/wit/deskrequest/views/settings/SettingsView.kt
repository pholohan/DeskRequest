package org.wit.deskrequest.views.settings

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_settings.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.deskrequest.R
import org.wit.deskrequest.views.BaseView

class SettingsView : BaseView(), AnkoLogger {

  lateinit var presenter: SettingsPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_settings)
    init(toolbar, true)

    presenter = initPresenter(SettingsPresenter(this)) as SettingsPresenter
    val bottomNavigationView =
        findViewById<View>(R.id.bottomNavSettings) as BottomNavigationView

    bottomNavigationView.setOnNavigationItemSelectedListener { item ->
      when (item.itemId) {
        R.id.item_book -> presenter.loadWelcome()
        //R.id.item_room -> presenter.doShowHillfortsMap()
        R.id.item_bookings -> presenter.loadBookings()
        R.id.item_settings -> presenter.userSettings()
        R.id.item_logout -> presenter.doLogout()
      }
      true
    }


    val user = FirebaseAuth.getInstance().currentUser
    user?.let {
      edit_email.setText(user.email)
    }

    updateEmail.setOnClickListener {
      var email = edit_email.text.toString()
      if (email != user?.email) {
        presenter.doUpdateEmail(email)
      } else {
        toast("Email is Already registered")
      }
    }


    updatePassword.setOnClickListener {
      var password = edit_password.text.toString()
      var confirmpassword = confirm_password.text.toString()
      info("Edit Password: $password")
      info("Confirm Password: $confirmpassword")
      if (password.equals(confirmpassword)) {
        presenter.doUpdatePassword(password)
      } else {
        toast("Password and Confirmed do not match")
      }
    }

    cancel.setOnClickListener{finish()}
  }
}