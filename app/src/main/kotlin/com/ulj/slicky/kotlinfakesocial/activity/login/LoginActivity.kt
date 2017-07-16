package com.ulj.slicky.kotlinfakesocial.activity.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.ulj.slicky.kotlinfakesocial.R
import kotlinx.android.synthetic.main.login_activity.login_container as viewPager
import kotlinx.android.synthetic.main.login_activity.login_indicator as pageIndicator

/**
 * Created by SlickyPC on 17.5.2017
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val adapter = LoginAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        pageIndicator.setViewPager(viewPager)
    }

    override fun onBackPressed() {
        if (viewPager.currentItem > 0)
            viewPager.currentItem = 0
        else
            super.onBackPressed()
    }

    @Suppress("UNUSED_PARAMETER")
    fun onMoveToSignUpClick(view: View) {
        viewPager.currentItem = 1
    }

    @Suppress("UNUSED_PARAMETER")
    fun onMoveToSignInClick(view: View) {
        viewPager.currentItem = 0
    }

    @Suppress("UNUSED_PARAMETER")
    fun onLostDetailsClick(view: View) {
        Toast.makeText(this, "Well, that sucks.", Toast.LENGTH_SHORT).show()
    }
}
