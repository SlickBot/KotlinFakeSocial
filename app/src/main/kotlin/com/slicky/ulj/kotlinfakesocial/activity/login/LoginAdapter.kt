package com.slicky.ulj.kotlinfakesocial.activity.login

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by SlickyPC on 18.5.2017
 */
internal class LoginAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val signinFragment = SignInFragment.newInstance()
    private val signupFragment = SignUpFragment.newInstance()

    override fun getItem(position: Int): Fragment {
        when (position) {
            1 -> return signupFragment
            else -> return signinFragment
        }
    }

    override fun getCount() = 2
}
