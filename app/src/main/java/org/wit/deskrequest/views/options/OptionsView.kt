package org.wit.deskrequest.views.options

import android.os.Bundle
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
      presenter.showRoomsList()
    }

    buttonOffice.setOnClickListener{
      presenter.showRoomsList()
    }

    buttonAll.setOnClickListener{
      presenter.showRoomsList()
    }

  }

}