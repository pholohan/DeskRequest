package org.wit.deskrequest.models

interface RoomStore {
  fun findAll(): List<RoomModel>
  fun findAllDesks(): List<Desk>
  fun findFavourites(): List<RoomModel>
  fun filterOffice(): List<RoomModel>
  fun filterMeetConf(): List<RoomModel>
  fun filterDesks(): List<Desk>
  fun create(room: RoomModel)
  fun update(room: RoomModel)
  fun delete(room: RoomModel)
  fun findById(id:Long) : RoomModel?
  fun clear()
}