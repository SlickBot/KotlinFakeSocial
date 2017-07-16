package com.ulj.slicky.kotlinfakesocial.activity.service

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Vibrator
import android.support.v7.app.NotificationCompat
import com.ulj.slicky.kotlinfakesocial.FakePreferences
import com.ulj.slicky.kotlinfakesocial.R
import com.ulj.slicky.kotlinfakesocial.activity.content.ContentActivity
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by SlickyPC on 30.5.2017
 */
class NotifyingService : IntentService("NotifyingService") {

    companion object {
        private const val NOTIFY_ID = 0xDEAD_BEEF.toInt()
        private const val CHANNEL_ID = "0xDEAD_BEEF"
        private const val CHANNEL_NAME = "NotifyingChannel"
    }

    private lateinit var prefs: FakePreferences

    override fun onCreate() {
        super.onCreate()
        prefs = FakePreferences(this)

        if (SDK_INT >= 26) {
            val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW).apply {
                enableLights(true)
                enableVibration(true)
                vibrationPattern = longArrayOf(500)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(notificationChannel)
        }
    }

    override fun onHandleIntent(intent: Intent) {
        with(prefs) {
            if (isNotifyOn) {
                val sleepInSeconds = if (isNotifyRandom) Random().nextInt(60) else notifyDuration
                TimeUnit.SECONDS.sleep(sleepInSeconds.toLong())
                displayNotification()
            }
        }
    }

    private fun displayNotification() {
        prefs.isNotifyOn = false

        val callbackIntent = Intent(applicationContext, ContentActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, callbackIntent, 0)

        val notification = NotificationCompat.Builder(this).apply {
            setSmallIcon(R.drawable.logo)
            setContentIntent(pendingIntent)
            setContentTitle(getString(R.string.app_name))
            setSubText("Time ran out!")
            setContentText("You should open Kotlin Fake Social again!")
            setAutoCancel(true)
        }.build()

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFY_ID, notification)

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        @Suppress("DEPRECATION")
        vibrator.vibrate(500)
    }
}
