package org.wit.deskrequest.views.welcome

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.wit.deskrequest.R
import org.wit.deskrequest.views.BaseView

class WelcomeView : BaseView() {

    lateinit var presenter: WelcomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        //presenter = initPresenter(WelcomePresenter(this)) as WelcomePresenter
    }
}