package org.wit.deskrequest.views.conf

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_conf_details.*
import kotlinx.android.synthetic.main.activity_room_list.*
import org.wit.deskrequest.R
import org.wit.deskrequest.models.RoomBookingModel
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.BaseView

class ConfView: BaseView() {

  lateinit var presenter: ConfPresenter
  var room = RoomModel()
  var roombooking = RoomBookingModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_conf_details)

    setSupportActionBar(toolbarList)
    super.init(toolbar, true);

    presenter = initPresenter(ConfPresenter(this)) as ConfPresenter
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

    bookConf.setOnClickListener{

      var id: Int = duration_rg.checkedRadioButtonId
      var id2: Int = duration_rg2.checkedRadioButtonId
      var duration = ""
      var half_day_duration = ""
      var full_day_duration = ""
      if (id!=-1)
      {
        var radio: RadioButton = findViewById(id)
        half_day_duration = radio.text as String
      }
      else{
        // If no radio button checked in this radio group
        Toast.makeText(applicationContext, "Please Select AM/PM",
            Toast.LENGTH_SHORT).show()
      }
      if (id2!=-1)
      {
        var radio: RadioButton = findViewById(id2)
        full_day_duration = radio.text as String
      }
      else{
        // If no radio button checked in this radio group
        Toast.makeText(applicationContext, "Please Select Full/Half Day",
            Toast.LENGTH_SHORT).show()
      }
      duration = full_day_duration +" "+ half_day_duration
      //presenter.doAddRoomBooking(deskNoField.text.toString().toLong(), duration)
      //presenter.doUpdateDeskBooked(true)
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

  override fun showRoom(room: RoomModel){
    confNameField.setText((room.roomName))
    whiteboardField.setText((room.whiteboard.toString()))
    speakerTypeField.setText(room.speakertype)
    screenSizeField.setText(room.screensize)
    coffee.setText(room.coffee.toString())
  }
}