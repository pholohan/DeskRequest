package org.wit.deskrequest.views

import android.content.Intent
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import org.jetbrains.anko.AnkoLogger
import org.wit.deskrequest.views.options.OptionsView
import org.wit.deskrequest.views.welcome.WelcomeView

enum class VIEW {
    WELCOME, OPTIONS
}

open abstract class BaseView() : AppCompatActivity(), AnkoLogger {

    var basePresenter: BasePresenter? = null

    fun navigateTo(view: VIEW, code: Int = 0, key: String = "", value: Parcelable? = null) {
        var intent = Intent(this, WelcomeView::class.java)
        when (view) {
            VIEW.WELCOME -> intent = Intent(this, WelcomeView::class.java)
            VIEW.OPTIONS -> intent = Intent(this, OptionsView::class.java)
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
        //val user = FirebaseAuth.getInstance().currentUser
        //if (user != null) {
        //    toolbar.title = "${title}: ${user.email}"
        //}
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

}