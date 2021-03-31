package org.wit.deskrequest.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RoomModel(var roomid: Long=0,
                     var roomName: String = "",
                     var roomType: String = "",
                     var location: String = "",
                     var capacity: String = "",
                     var roombooked: Boolean = false): Parcelable