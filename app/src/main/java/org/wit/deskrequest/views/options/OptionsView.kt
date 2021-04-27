package org.wit.deskrequest.views.options

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_book_options.*
import org.wit.deskrequest.R
import org.wit.deskrequest.views.BaseView


class OptionsView : BaseView() {

  lateinit var presenter: OptionsPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_book_options)

    setSupportActionBar(toolbarBook)
    super.init(toolbarBook, true);

    presenter = initPresenter(OptionsPresenter(this)) as OptionsPresenter

    buttonConf.setOnClickListener{
      presenter.showMeetConf()
    }

    buttonOffice.setOnClickListener{
      presenter.showOffice()
    }

    buttonAll.setOnClickListener{
      presenter.showRoomsList()
    }

    val bottomNavigationView =
        findViewById<View>(R.id.bottomNav) as BottomNavigationView

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

  }

}