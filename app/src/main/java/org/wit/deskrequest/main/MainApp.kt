package org.wit.deskrequest.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.deskrequest.models.RoomMemStore
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.models.RoomStore

class MainApp : Application(), AnkoLogger {

    //val rooms = ArrayList<RoomModel>()
    val rooms = RoomMemStore()
    //lateinit var rooms: RoomStore
    //lateinit var users: UserStore
    //lateinit var loggedinuser: LoggedInUserMemStore

    override fun onCreate() {
        super.onCreate()
        //hillforts = HillfortJSONStore(applicationContext)
        //users = UserMemStore()
        //hillforts = HillfortStoreRoom(applicationContext)
        //hillforts = HillfortFireStore(applicationContext)
        //users = UserJSONStore(applicationContext)
        //loggedinuser = LoggedInUserMemStore()
        info("Desk Request App started")
    }
}