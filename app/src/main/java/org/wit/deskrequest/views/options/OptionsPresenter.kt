package org.wit.deskrequest.views.options

import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView
import org.wit.deskrequest.views.VIEW

class OptionsPresenter(view: BaseView) : BasePresenter(view){

  fun showMeetConf() {
    view?.navigateTo(VIEW.MEETCONF)
  }

  fun showOffice() {
    view?.navigateTo(VIEW.OFFICE)
  }

  fun showRoomsList() {
    view?.navigateTo(VIEW.LIST)
  }

}