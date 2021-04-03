package org.wit.deskrequest.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


var lastId = 0L

internal fun getId(): Long {
  return lastId++
}

class RoomMemStore : RoomStore, AnkoLogger {

  val rooms = ArrayList<RoomModel>()

  override fun findAll(): List<RoomModel> {
    rooms.add(RoomModel(1000, "IT", "Office", "301", "10"))
    rooms.add(RoomModel(1001, "Bunmahon Room", "Conf", "27/35", "20"))
    rooms.add(RoomModel(1002, "QA", "Office", "33/35", "8"))
    rooms.add(RoomModel(1003, "Reginald Room", "Meeting", "301", "50"))
    rooms.add(RoomModel(1004, "Tech Support", "Office", "27/35", "15"))
    rooms.add(RoomModel(1005, "QC", "Office", "301", "10"))
    rooms.add(RoomModel(1006, "Dunhill Room", "Meeting", "27/35", "20"))
    rooms.add(RoomModel(1007, "R&D Devices", "Office", "301", "8"))
    rooms.add(RoomModel(1008, "Tramore Room", "Meeting", "301", "50"))
    rooms.add(RoomModel(1009, "Pharm Dev", "Office", "27/35", "15"))
    return rooms
  }

  override fun create(room: RoomModel) {
    room.roomid = getId()
    rooms.add(room)
    logAll();
  }

  override fun findFavourites(): List<RoomModel> {
    val favRooms: List<RoomModel> = rooms.filter { p -> p.roombooked == true }
   return favRooms
  }

  override fun filterOffice(name: String): List<RoomModel> {
    val filterOffices: List<RoomModel> = rooms.filter { p -> p.roomType == "Office" }
    return filterOffices
  }

  override fun filterMeetConf(name: String): List<RoomModel> {
    val filterMeetConfs: List<RoomModel> = rooms.filter { p -> p.roomType == "Meeting" || p.roomType == "Conf" }
    return filterMeetConfs
  }

  override fun update(room: RoomModel) {
    var foundRoom: RoomModel? = rooms.find { p -> p.roomid == room.roomid }
    if (foundRoom != null) {
      foundRoom.roomName = room.roomName
      foundRoom.roomType = room.roomType
      foundRoom.location = room.location
      foundRoom.capacity = room.capacity
      logAll()
    }
  }

  override fun delete(room: RoomModel) {
    var foundRoom: RoomModel? = rooms.find { p -> p.roomid == room.roomid }
    if (foundRoom != null) {
      rooms.remove(foundRoom)
      logAll()
    }
  }

  fun logAll() {
    rooms.forEach{ info("${it}") }
  }

  override fun findById(id:Long) : RoomModel? {
    val foundRoom: RoomModel? = rooms.find { it.roomid == id }
    return foundRoom
  }

  override fun clear() {
    rooms.clear()
  }
}