package org.wit.deskrequest.models.json

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.deskrequest.helpers.exists
import org.wit.deskrequest.helpers.read
import org.wit.deskrequest.helpers.write
import org.wit.deskrequest.models.Desk
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.models.RoomStore
import java.util.*

val JSON_FILE = "rooms.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<RoomModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class RoomJSONStore : RoomStore, AnkoLogger {

    val context: Context
    var rooms = mutableListOf<RoomModel>()
    var desks = mutableListOf<Desk>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<RoomModel> {
        return rooms
    }

    override fun findAllDesks(): MutableList<Desk> {
        return desks
    }

    override fun findFavourites(): List<RoomModel> {
        val favRooms: List<RoomModel> = rooms.filter { p -> p.roombooked == true }
        return favRooms
    }

    override fun filterOffice(): List<RoomModel> {
        val filterOffices: List<RoomModel> = rooms.filter { p -> p.roomType == "Office" }
        return filterOffices
    }

    //override fun filterDesks(id:Long): List<Desk> {
    //    val filterRooms: List<RoomModel> = rooms.filter { p -> p.roomType == "Office" }
    //    var filteredDesks: List<Desk> = arrayListOf()
    //    filterRooms.forEach{
    //         filteredDesks = it.desk
    //        info("Filtered Desks: $filteredDesks")
    //    }
     //   return filteredDesks
    //}
    override fun filterDesks(id:Long): List<Desk>? {
        val filterRoom: List<RoomModel> = rooms.filter { p -> p.roomid == id }
        var filteredDesks: List<Desk>? = arrayListOf()
        filterRoom.forEach{
             filteredDesks = it.desk
            info("Filtered Desks: $filteredDesks")
        }
       return filteredDesks
    }

    override fun updateDeskBooked(desk : Desk) {
        //var foundDesk: Desk? = desks.find { p -> p.deskid == desk.deskid }
        var foundDesk = desk
        if(foundDesk!=null){
            foundDesk.deskid = desk.deskid
            foundDesk.deskbooked = true
            foundDesk.chair.type = desk.chair.type
            foundDesk.chair.size = desk.chair.size
            foundDesk.computer.os = desk.computer.os
            foundDesk.monitor.size = desk.monitor.resolution
            foundDesk.dock.model = desk.dock.model
            foundDesk.phone.phno = desk.phone.phno
            foundDesk.phone.directdial = desk.phone.directdial
            info("Sending to Desk $foundDesk")
            deskserialize()
        }
    }


    override fun filterMeetConf(): List<RoomModel> {
        val filterMeetConfs: List<RoomModel> = rooms.filter { p -> p.roomType == "Meeting" || p.roomType == "Conf"}
        return filterMeetConfs
    }

    override fun create(room: RoomModel) {
        room.roomid = generateRandomId()
        rooms.add(room)
        serialize()
    }


    override fun update(room: RoomModel) {
        var foundRoom: RoomModel? = rooms.find { p -> p.roomid == room.roomid }
        if (foundRoom != null) {
            foundRoom.roomName = room.roomName
            foundRoom.roomType = room.roomType
            foundRoom.location = room.location
            foundRoom.capacity = room.capacity
            serialize()
        }
    }

    override fun delete(room: RoomModel) {
        var foundRoom: RoomModel? = rooms.find { p -> p.roomid == room.roomid }
        rooms.remove(foundRoom)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(rooms, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deskserialize() {
        val jsonString = gsonBuilder.toJson(desks, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        rooms = Gson().fromJson(jsonString, listType)
    }

    override fun findById(id:Long) : RoomModel? {
        val foundRoom: RoomModel? = rooms.find { it.roomid == id }
        return foundRoom
    }

    override fun findDeskById(roomid:Long, deskid:Long) : Desk? {
            val filterRoom: List<RoomModel> = rooms.filter { p -> p.roomid == roomid }
            var filteredDesks: List<Desk>? = arrayListOf()
            filterRoom.forEach {
                filteredDesks = it.desk
            }
            info("Filtered Desks: $filteredDesks")
            val updateDesk: Desk? = filteredDesks?.find{it.deskid == deskid}
            return updateDesk
    }

    override fun clear() {
        rooms.clear()
    }
}