package org.wit.deskrequest.views.welcome

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_room_list.*
import org.wit.deskrequest.R
import org.wit.deskrequest.views.BaseView
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.activity_welcome.toolbar
import org.jetbrains.anko.info
import org.wit.deskrequest.models.BookingModel
import org.wit.deskrequest.views.desk.DeskView
import java.text.DateFormat
import java.util.*


class WelcomeView : BaseView() {

    var booking = BookingModel()
    //var booking_date = ""
    val calendar = Calendar.getInstance()

    companion object {
        var booking_date =""
    }

    lateinit var presenter: WelcomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        setSupportActionBar(toolbar)
        super.init(toolbar, true);

        presenter = initPresenter(WelcomePresenter(this)) as WelcomePresenter


        calendarView.setOnDateChangeListener{view, year, month, dayOfMonth ->
            // set the calendar date as calendar view selected date
            calendar.set(year,month,dayOfMonth)

            // set this date as calendar view selected date
            calendarView.date = calendar.timeInMillis

            // format the calendar view selected date
            val dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM)
            booking_date = dateFormatter.format(calendar.time)

        }

        

        buttonContinue.setOnClickListener{
            presenter.showOptions()
            info("Booking Date: $booking_date")
        }
    }
}