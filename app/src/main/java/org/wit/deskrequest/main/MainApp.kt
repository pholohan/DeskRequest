package org.wit.deskrequest.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.deskrequest.models.RoomModel

class MainApp : Application(), AnkoLogger {

    val rooms = ArrayList<RoomModel>()
    //lateinit var hillforts: HillfortStore
    //lateinit var users: UserStore
    //lateinit var loggedinuser: LoggedInUserMemStore

    override fun onCreate() {
        super.onCreate()
        rooms.add(RoomModel(1000, "IT", "Office", "301", "10"))
        rooms.add(RoomModel(1001, "Bunmahon Room", "Conf", "27/35", "20"))
        rooms.add(RoomModel(1002, "QA", "Office", "33/35", "8"))
        rooms.add(RoomModel(1003, "Reginald Room", "Meeting", "301", "50"))
        rooms.add(RoomModel(1004, "Tech Support", "Office", "27/35", "15"))
        //hillforts = HillfortJSONStore(applicationContext)
        //users = UserMemStore()
        //hillforts = HillfortStoreRoom(applicationContext)
        //hillforts = HillfortFireStore(applicationContext)
        //users = UserJSONStore(applicationContext)
        //loggedinuser = LoggedInUserMemStore()
        info("Desk Request App started")
    }
}