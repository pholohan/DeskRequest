package org.wit.deskrequest.views.welcome

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_room_list.*
import org.wit.deskrequest.R
import org.wit.deskrequest.views.BaseView
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.activity_welcome.toolbar


class WelcomeView : BaseView() {

    lateinit var presenter: WelcomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        setSupportActionBar(toolbar)
        super.init(toolbar, true);

        presenter = initPresenter(WelcomePresenter(this)) as WelcomePresenter

        buttonContinue.setOnClickListener{
            presenter.showOptions()
        }
    }
}