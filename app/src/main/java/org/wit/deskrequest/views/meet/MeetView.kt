package org.wit.deskrequest.views.meet

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_desk_details.*
import kotlinx.android.synthetic.main.activity_desk_details.duration_rg
import kotlinx.android.synthetic.main.activity_desk_details.duration_rg2
import kotlinx.android.synthetic.main.activity_desk_details.toolbar
import kotlinx.android.synthetic.main.activity_meet_details.*
import kotlinx.android.synthetic.main.activity_room_list.*
import org.jetbrains.anko.info
import org.wit.deskrequest.R
import org.wit.deskrequest.models.RoomBookingModel
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.BaseView

class MeetView : BaseView() {

  lateinit var presenter: MeetPresenter
  var room = RoomModel()
  var roombooking = RoomBookingModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_meet_details)

    setSupportActionBar(toolbarList)
    super.init(toolbar, true);

    presenter = initPresenter(MeetPresenter(this)) as MeetPresenter
    //presenter.loadDesks()
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

    bookMeet.setOnClickListener{

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
  }

  override fun showRoom(room: RoomModel){
    meetNameField.setText((room.roomName))
    whiteboardField.setText((room.whiteboard.toString()))
    projectorField.setText(room.projector.toString())
    desktopField.setText(room.desktop.toString())
    laptopField.setText(room.laptop.toString())
  }
}