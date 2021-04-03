package org.wit.deskrequest.models

interface RoomStore {
  fun findAll(): List<RoomModel>
  fun findFavourites(): List<RoomModel>
  fun filterOffice(name: String): List<RoomModel>
  fun filterMeetConf(name: String): List<RoomModel>
  fun create(room: RoomModel)
  fun update(room: RoomModel)
  fun delete(room: RoomModel)
  fun findById(id:Long) : RoomModel?
  fun clear()
}