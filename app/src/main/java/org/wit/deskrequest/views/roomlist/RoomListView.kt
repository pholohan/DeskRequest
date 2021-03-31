package org.wit.deskrequest.views.roomlist

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_book_options.*
import org.wit.deskrequest.R
import org.wit.deskrequest.views.BaseView

class RoomListView : BaseView() {

  lateinit var presenter: RoomListPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_room_list)

    setSupportActionBar(toolbarBook)
    super.init(toolbarBook, true);
  }
}