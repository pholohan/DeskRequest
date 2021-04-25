package org.wit.deskrequest.views.desk

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_desk_details.*
import kotlinx.android.synthetic.main.activity_room_list.*
import kotlinx.android.synthetic.main.card_desk.*
import org.jetbrains.anko.info
import org.wit.deskrequest.R
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.models.Desk
import org.wit.deskrequest.views.BaseView



class DeskView : BaseView() {

  lateinit var presenter: DeskPresenter
  var desk = Desk()
  var booking = BookingModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_desk_details)

    setSupportActionBar(toolbarList)
    super.init(toolbar, true);

    presenter = initPresenter(DeskPresenter(this)) as DeskPresenter
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

    bookDesk.setOnClickListener{

        var id: Int = duration_rg.checkedRadioButtonId
        var id2: Int = duration_rg2.checkedRadioButtonId
        var duration = ""
        var half_day_duration = ""
        var full_day_duration = ""
        if (id!=-1)
        {
          var radio:RadioButton = findViewById(id)
          half_day_duration = radio.text as String
        }
        else{
          // If no radio button checked in this radio group
          Toast.makeText(applicationContext, "Please Select AM/PM",
              Toast.LENGTH_SHORT).show()
        }
      if (id2!=-1)
      {
        var radio:RadioButton = findViewById(id2)
        full_day_duration = radio.text as String
      }
      else{
        // If no radio button checked in this radio group
        Toast.makeText(applicationContext, "Please Select Full/Half Day",
            Toast.LENGTH_SHORT).show()
      }
        duration = full_day_duration +" "+ half_day_duration
        presenter.doAddBooking(deskNoField.text.toString().toLong(), duration)
        //presenter.doUpdateDeskBooked(deskNoField.text.toString().toLong())
        info("Desk to Update: $desk")
    }

  }

  override fun showDesk(desk: Desk){
    deskNoField.setText((desk.deskid.toString()))
    chairSizeField.setText(desk.chair.size)
    chairTypeField.setText(desk.chair.type)
    compOSField.setText(desk.computer.os)
    monSizeField.setText(desk.monitor.size)
    monResField.setText(desk.monitor.resolution)
    docModelField.setText(desk.dock.model)
    phNoField.setText((desk.phone.phno).toString())
    directDialField.setText((desk.phone.directdial.toString()))
  }
}