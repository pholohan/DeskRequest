package org.wit.deskrequest.views.desk

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_room_list.*
import org.wit.deskrequest.R
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.desklist.DeskListPresenter
import org.wit.deskrequest.views.desklist.DeskListener

class DeskView : BaseView() {

  lateinit var presenter: DeskPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_desk_details)

    setSupportActionBar(toolbarList)
    super.init(toolbarList, true);

    presenter = initPresenter(DeskPresenter(this)) as DeskPresenter
    //presenter.loadDesks()
  }
}