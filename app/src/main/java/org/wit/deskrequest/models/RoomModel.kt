package org.wit.deskrequest.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class RoomModel(var roomid: Long=0,
                     var roomName: String = "",
                     var roomType: String = "",
                     var location: String = "",
                     var capacity: String = "",
                     var desk: ArrayList<Desk>,
                     var roombooked: Boolean = false): Parcelable

@Parcelize
data class Desk(var deskid: Long =0,
                var deskbooked: Boolean = false,
                var chair: Chair = Chair(),
                var computer: Computer = Computer(),
                var monitor: Monitor = Monitor(),
                var dock: Dock = Dock(),
                var phone: Phone = Phone()):Parcelable
@Parcelize
data class Chair(var chairid: Long =0,
                 var type: String = "",
                 var size: String = ""):Parcelable

@Parcelize
data class Computer(var assetid: Long = 0,
                    var os: String = ""):Parcelable

@Parcelize
data class Monitor(var monid: String = "",
                   var size: String = "",
                   var resolution: String = ""):Parcelable

@Parcelize
data class Dock(var dockid: String = "",
                var model: String = ""):Parcelable

@Parcelize
data class Phone(var phno: String = "",
                 var directdial: Boolean = false):Parcelable
