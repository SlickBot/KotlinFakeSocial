package com.slicky.ulj.kotlinfakesocial.activity.login

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.rd.PageIndicatorView
import com.slicky.ulj.kotlinfakesocial.R
import com.slicky.ulj.kotlinfakesocial.findView

/**
 * Created by SlickyPC on 17.5.2017
 */
class LoginActivity : AppCompatActivity() {

    private val viewPager by findView<ViewPager>(R.id.container)
    private val pageIndicator by findView<PageIndicatorView>(R.id.indicator)

    private lateinit var adapter: LoginAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        adapter = LoginAdapter(supportFragmentManager)
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
    fun onSignInClick(view: View) {
        adapter.signinFragment.trySignin()
    }

    @Suppress("UNUSED_PARAMETER")
    fun onSignUpClick(view: View) {
        adapter.signupFragment.trySignup()
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
