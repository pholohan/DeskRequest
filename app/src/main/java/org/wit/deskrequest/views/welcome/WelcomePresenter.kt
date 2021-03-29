package org.wit.deskrequest.views.welcome

import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW

class WelcomePresenter (view: BaseView) : BasePresenter(view)  {

  fun showOptions() {
    view?.navigateTo(VIEW.OPTIONS)
  }
}