package org.wit.deskrequest.views.desk

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_desk_details.*
import kotlinx.android.synthetic.main.activity_room_list.*
import org.wit.deskrequest.R
import org.wit.deskrequest.models.Desk
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.desklist.DeskListPresenter
import org.wit.deskrequest.views.desklist.DeskListener

class DeskView : BaseView() {

  lateinit var presenter: DeskPresenter
  var desk = Desk()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_desk_details)

    setSupportActionBar(toolbarList)
    super.init(toolbar, true);

    presenter = initPresenter(DeskPresenter(this)) as DeskPresenter
    //presenter.loadDesks()

    bookDesk.setOnClickListener{

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
    phNoField.setText(desk.phone.phno)
    directDialField.setText((desk.phone.directdial.toString()))
  }
}