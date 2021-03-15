package org.wit.deskrequest.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp : Application(), AnkoLogger {

    //val hillforts = ArrayList<HillfortModel>()
    //lateinit var hillforts: HillfortStore
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