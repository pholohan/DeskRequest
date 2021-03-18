package org.wit.deskrequest.views.options

import android.os.Bundle
import org.wit.deskrequest.R
import org.wit.deskrequest.views.BaseView


class OptionsView : BaseView() {

  lateinit var presenter: OptionsPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_book_options)

  }
}