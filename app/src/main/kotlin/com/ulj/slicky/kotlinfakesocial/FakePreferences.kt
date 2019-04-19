package com.ulj.slicky.kotlinfakesocial

import android.content.Context

/**
 * Created by SlickyPC on 30.5.2017
 */
class FakePreferences(context: Context) {

    companion object {
        private val TAG = FakePreferences::class.java.canonicalName
        private const val NOTIFY_ON_OFF = "notifyOnOff"
        private const val NOTIFY_RANDOM = "notifyRandom"
        private const val NOTIFY_DURATION = "notifyDuration"
    }

    private val prefs = context.getSharedPreferences(TAG, Context.MODE_PRIVATE)

    var isNotifyOn: Boolean
        get() = prefs.getBoolean(NOTIFY_ON_OFF, false)
        set(isOn) = prefs.edit().putBoolean(NOTIFY_ON_OFF, isOn).apply()

    var isNotifyRandom: Boolean
        get() = prefs.getBoolean(NOTIFY_RANDOM, false)
        set(isRandom) = prefs.edit().putBoolean(NOTIFY_RANDOM, isRandom).apply()

    var notifyDuration: Int
        get() = prefs.getInt(NOTIFY_DURATION, 10)
        set(duration) = prefs.edit().putInt(NOTIFY_DURATION, duration).apply()

}
