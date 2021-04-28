package org.wit.deskrequest.views

import android.content.Intent
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.models.Desk
import org.wit.deskrequest.models.RoomModel
import org.wit.deskrequest.views.booking.BookingView
import org.wit.deskrequest.views.bookinglist.BookingListView
import org.wit.deskrequest.views.desk.DeskView
import org.wit.deskrequest.views.desklist.DeskListView
import org.wit.deskrequest.views.login.LoginView
import org.wit.deskrequest.views.meetconflist.MeetConfListView
import org.wit.deskrequest.views.officelist.OfficeListView
import org.wit.deskrequest.views.options.OptionsView
import org.wit.deskrequest.views.roomlist.RoomListPresenter
import org.wit.deskrequest.views.welcome.WelcomeView
import org.wit.deskrequest.views.roomlist.RoomListView
import org.wit.deskrequest.views.settings.SettingsView
import org.wit.deskrequest.views.welcome.WelcomePresenter

enum class VIEW {
    WELCOME, OPTIONS, LIST, MEETCONF, OFFICE, DESK, DESKDETAILS, ROOMDETAILS, BOOKINGS, BOOKDETAILS, LOGIN, SETTINGS
}

open abstract class BaseView() : AppCompatActivity(), AnkoLogger {

    var basePresenter: BasePresenter? = null

    fun navigateTo(view: VIEW, code: Int = 0, key: String = "", value: Parcelable? = null) {
        var intent = Intent(this, WelcomeView::class.java)
        when (view) {
            VIEW.WELCOME -> intent = Intent(this, WelcomeView::class.java)
            VIEW.OPTIONS -> intent = Intent(this, OptionsView::class.java)
            VIEW.LIST -> intent = Intent(this, RoomListView::class.java)
            VIEW.MEETCONF -> intent = Intent(this, MeetConfListView::class.java)
            VIEW.OFFICE -> intent = Intent(this, OfficeListView::class.java)
            VIEW.DESK -> intent = Intent(this, DeskListView::class.java)
            VIEW.DESKDETAILS -> intent = Intent(this, DeskView::class.java)
            //VIEW.ROOMDETAILS -> intent = Intent(this, DeskView::class.java)
            VIEW.BOOKINGS -> intent = Intent(this, BookingListView::class.java)
            VIEW.BOOKDETAILS -> intent = Intent(this, BookingView::class.java)
            VIEW.LOGIN -> intent = Intent(this, LoginView::class.java)
            VIEW.SETTINGS -> intent = Intent(this, SettingsView::class.java)
        }
        if (key != "") {
            intent.putExtra(key, value)
        }
        startActivityForResult(intent, code)
    }

    fun initPresenter(presenter: BasePresenter): BasePresenter {
        basePresenter = presenter
        return presenter
    }

    fun init(toolbar: Toolbar, upEnabled: Boolean) {
        toolbar.title = title
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(upEnabled)
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            toolbar.title = "${title}: ${user.email}"
        }
    }

    override fun onDestroy() {
        basePresenter?.onDestroy()
        super.onDestroy()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            basePresenter?.doActivityResult(requestCode, resultCode, data)
        }
    }

    //override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
    //    basePresenter?.doRequestPermissionsResult(requestCode, permissions, grantResults)
    //}

    open fun showRooms(rooms: List<RoomModel>) {}
    open fun showDesks(desk: List<Desk>) {}
    open fun showDesk(desk: Desk) {}
    open fun showBookings(bookings: List<BookingModel>) {}
    open fun showBooking(booking: BookingModel){}
    open fun showProgress() {}
    open fun hideProgress() {}
}