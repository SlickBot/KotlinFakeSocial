package com.ulj.slicky.kotlinfakesocial

import android.content.Context

/**
 * Created by SlickyPC on 30.5.2017
 */
class FakePreferences(context: Context) {

    private val TAG = FakePreferences::class.java.canonicalName
    private val prefs = context.getSharedPreferences(TAG, Context.MODE_PRIVATE)

    private val NOTIFY_ON_OFF = "notifyOnOff"
    var isNotifyOn: Boolean
        get() = prefs.getBoolean(NOTIFY_ON_OFF, false)
        set(isOn) = prefs.edit().putBoolean(NOTIFY_ON_OFF, isOn).apply()

    private val NOTIFY_RANDOM = "notifyRandom"
    var isNotifyRandom: Boolean
        get() = prefs.getBoolean(NOTIFY_RANDOM, false)
        set(isRandom) = prefs.edit().putBoolean(NOTIFY_RANDOM, isRandom).apply()

    private val NOTIFY_DURATION = "notifyDuration"
    var notifyDuration: Int
        get() = prefs.getInt(NOTIFY_DURATION, 10)
        set(duration) = prefs.edit().putInt(NOTIFY_DURATION, duration).apply()
}
