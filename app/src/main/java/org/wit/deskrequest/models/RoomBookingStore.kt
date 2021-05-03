package org.wit.deskrequest.models

interface RoomBookingStore {
  fun findAll(): List<RoomBookingModel>
  fun create(roombooking: RoomBookingModel)
  fun update(roombooking: RoomBookingModel)
  fun delete(roombooking: RoomBookingModel)
  fun clear()
}