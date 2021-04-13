package org.wit.deskrequest.views.desk

import org.wit.deskrequest.models.Desk
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.BasePresenter
import org.wit.deskrequest.views.BaseView

class DeskPresenter (view: BaseView) : BasePresenter(view){

  var desk = Desk()

  init {
    if (view.intent.hasExtra("desk")) {
      desk = view.intent.extras?.getParcelable<Desk>("desk")!!
      view.showDesk(desk)
    } else {
    }
  }

}