package org.wit.deskrequest.models

interface BookingStore {
  fun findAll(): List<BookingModel>
  fun create(booking: BookingModel)
  fun update(booking: BookingModel)
  fun delete(booking: BookingModel)
}