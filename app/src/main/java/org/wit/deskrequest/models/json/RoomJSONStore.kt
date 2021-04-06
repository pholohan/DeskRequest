package org.wit.deskrequest.models.json

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.deskrequest.helpers.exists
import org.wit.deskrequest.helpers.read
import org.wit.deskrequest.helpers.write
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

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<RoomModel> {
        return rooms
    }

    override fun findFavourites(): List<RoomModel> {
        val favRooms: List<RoomModel> = rooms.filter { p -> p.roombooked == true }
        return favRooms
    }

    override fun filterOffice(): List<RoomModel> {
        val filterOffices: List<RoomModel> = rooms.filter { p -> p.roomType == "Office" }
        return filterOffices
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

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        rooms = Gson().fromJson(jsonString, listType)
    }

    override fun findById(id:Long) : RoomModel? {
        val foundRoom: RoomModel? = rooms.find { it.roomid == id }
        return foundRoom
    }

    override fun clear() {
        rooms.clear()
    }
}