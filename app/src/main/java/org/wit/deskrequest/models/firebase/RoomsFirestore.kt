package org.wit.deskrequest.models.firebase

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.models.RoomStore
import com.google.firebase.database.*
import com.google.gson.Gson
import org.jetbrains.anko.info
import org.wit.deskrequest.helpers.read
import org.wit.deskrequest.helpers.write
import org.wit.deskrequest.models.Desk
import org.wit.deskrequest.models.json.JSON_FILE
import org.wit.deskrequest.models.json.generateRandomId
import org.wit.deskrequest.models.json.gsonBuilder
import org.wit.deskrequest.models.json.listType

class RoomsFirestore(val context: Context) : RoomStore, AnkoLogger {

    val rooms = ArrayList<RoomModel>()
    val desks = ArrayList<Desk>()
    lateinit var userId: String
    lateinit var db: DatabaseReference


    override fun findAll(): List<RoomModel> {
        return rooms
    }

    override fun findAllDesks(): List<Desk> {
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
        }
    }


    override fun filterMeetConf(): List<RoomModel> {
        val filterMeetConfs: List<RoomModel> = rooms.filter { p -> p.roomType == "Meeting" || p.roomType == "Conf"}
        return filterMeetConfs
    }

    override fun create(room: RoomModel) {
        val key = db.child("rooms").push().key
        key?.let {
            //room.fbid = key
            db.child("rooms").child(key).setValue(room)
        }
     }


    override fun update(room: RoomModel) {
        var foundRoom: RoomModel? = rooms.find { p -> p.roomid == room.roomid }
        if (foundRoom != null) {
            foundRoom.roomName = room.roomName
            foundRoom.roomType = room.roomType
            foundRoom.location = room.location
            foundRoom.capacity = room.capacity
        }
    }

    override fun delete(room: RoomModel) {
        var foundRoom: RoomModel? = rooms.find { p -> p.roomid == room.roomid }
        rooms.remove(foundRoom)
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

    fun fetchRooms(roomsReady: () -> Unit) {
        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                info("Rooms Data $dataSnapshot")
                dataSnapshot!!.children.mapNotNullTo(rooms) { it.getValue<RoomModel>(RoomModel::class.java)}
                roomsReady()
            }
        }
        userId = FirebaseAuth.getInstance().currentUser!!.uid
        db = FirebaseDatabase.getInstance().reference
        rooms.clear()
        db.child("rooms").addListenerForSingleValueEvent(valueEventListener)
    }
}
