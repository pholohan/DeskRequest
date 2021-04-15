package org.wit.deskrequest.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookingModel(var dbookid: Long=0,
                        var deskid: Long=0,
                        var fbid: Long=0,
                        var d_date: String = "",
                        var d_duration: String = ""): Parcelable

