package org.wit.deskrequest.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class RoomModel(var roomid: Long = 0,
                     var roomName: String = "",
                     var roomType: String = "",
                     var location: String = "",
                     var capacity: String = "",
                     var desk: ArrayList<Desk>? = null,
                     var roombooked: Boolean? = false,
                     var whiteboard: Boolean? = false,
                     var projector: Boolean? = false,
                     var desktop: Boolean? = false,
                     var laptop: Boolean? = false,
                     var speakertype: String? = "",
                     var screensize: String? ="",
                     var coffee: Boolean? = false): Parcelable

@Parcelize
data class Desk(var deskid: Long = 0,
                var deskbooked: Boolean = false,
                var chair: Chair = Chair(),
                var computer: Computer = Computer(),
                var monitor: Monitor = Monitor(),
                var dock: Dock = Dock(),
                var phone: Phone = Phone()):Parcelable
@Parcelize
data class Chair(var chairid: Long = 0,
                 var type: String = "",
                 var size: String = ""):Parcelable

@Parcelize
data class Computer(var assetid: Long = 0,
                    var os: String = ""):Parcelable

@Parcelize
data class Monitor(var monid: Long = 0,
                   var size: String = "",
                   var resolution: String = ""):Parcelable

@Parcelize
data class Dock(var dockid: Long = 0,
                var model: String = ""):Parcelable

@Parcelize
data class Phone(var phno: Long = 0,
                 var directdial: Boolean? = false):Parcelable

