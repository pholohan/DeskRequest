package org.wit.deskrequest.views.welcome

import android.os.Bundle
import org.wit.deskrequest.R
import org.wit.deskrequest.views.BaseView
import kotlinx.android.synthetic.main.activity_book_options.*
import kotlinx.android.synthetic.main.activity_welcome.*


class WelcomeView : BaseView() {

    lateinit var presenter: WelcomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        presenter = initPresenter(WelcomePresenter(this)) as WelcomePresenter

        buttonContinue.setOnClickListener{
            presenter.showOptions()
        }
    }
}