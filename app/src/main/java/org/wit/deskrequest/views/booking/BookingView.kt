package org.wit.deskrequest.views.booking

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_booking_details.*
import kotlinx.android.synthetic.main.activity_desk_details.*
import kotlinx.android.synthetic.main.activity_desk_details.bookDesk
import kotlinx.android.synthetic.main.activity_desk_details.duration_rg
import kotlinx.android.synthetic.main.activity_desk_details.duration_rg2
import kotlinx.android.synthetic.main.activity_desk_details.toolbar
import kotlinx.android.synthetic.main.activity_room_list.*
import org.jetbrains.anko.info
import org.wit.deskrequest.R
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.models.Desk
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.desk.DeskPresenter

class BookingView : BaseView() {

  lateinit var presenter: BookingPresenter
  var desk = Desk()
  var booking = BookingModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_booking_details)

    setSupportActionBar(toolbarList)
    super.init(toolbar, true);

    presenter = initPresenter(BookingPresenter(this)) as BookingPresenter
    //presenter.loadDesks()

    duration_rg.setOnCheckedChangeListener(
        RadioGroup.OnCheckedChangeListener { group, checkedID ->
          val radio: RadioButton = findViewById(checkedID)
          Toast.makeText(applicationContext, " On checked change : ${radio.text}",
              Toast.LENGTH_SHORT).show()
        }
    )

    duration_rg2.setOnCheckedChangeListener(
        RadioGroup.OnCheckedChangeListener { group, checkedID ->
          val radio: RadioButton = findViewById(checkedID)
          Toast.makeText(applicationContext, " On checked change : ${radio.text}",
              Toast.LENGTH_SHORT).show()
        }
    )

    bookUpdate.setOnClickListener {

      var id: Int = duration_rg.checkedRadioButtonId
      var id2: Int = duration_rg2.checkedRadioButtonId
      var duration = ""
      var half_day_duration = ""
      var full_day_duration = ""
      if (id != -1) {
        var radio: RadioButton = findViewById(id)
        half_day_duration = radio.text as String
      } else {
        // If no radio button checked in this radio group
        Toast.makeText(applicationContext, "Please Select AM/PM",
            Toast.LENGTH_SHORT).show()
      }
      if (id2 != -1) {
        var radio: RadioButton = findViewById(id2)
        full_day_duration = radio.text as String
      } else {
        // If no radio button checked in this radio group
        Toast.makeText(applicationContext, "Please Select Full/Half Day",
            Toast.LENGTH_SHORT).show()
      }
      duration = full_day_duration + " " + half_day_duration
      presenter.doUpdateBooking(duration)
      info("Desk to Update: $desk")
    }

    bookCancel.setOnClickListener{
      presenter.doCancelBooking()
    }


    val bottomNavigationView =
        findViewById<View>(R.id.bottomNav) as BottomNavigationView

    bottomNavigationView.setOnNavigationItemSelectedListener { item ->
      when (item.itemId) {
        R.id.item_book -> presenter.loadWelcome()
        R.id.item_room_bookings -> presenter.loadRoomBookings()
        R.id.item_bookings -> presenter.loadBookings()
        R.id.item_settings -> presenter.userSettings()
        R.id.item_logout -> presenter.doLogout()
      }
      true
    }
  }

  override fun showBooking(booking: BookingModel){
    bookDeskNoField.setText((booking.deskid.toString()))
    bookRefField.setText(booking.dbookid.toString())
    bookDateField.setText(booking.d_date)
    bookdurField.setText(booking.d_duration)
  }
}