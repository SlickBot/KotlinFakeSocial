@file:Suppress("unused")

package com.ulj.slicky.kotlinfakesocial

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.os.Vibrator
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.text.format.DateFormat
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.TextView
import com.ulj.slicky.kotlinfakesocial.model.person.Person
import java.io.Serializable
import java.util.*

/**
 * Created by SlickyPC on 24.5.2017
 */
const val SHAKE_DURATION = 300
const val SHAKE_COUNT = 10

fun String.capitalizeAll() = split(" ").joinToString(separator = " ", transform = String::capitalize)

fun Person.fullName() = "${name.first.capitalizeAll()} ${name.last.capitalizeAll()}"

fun Person.fullNameWithTitle() = "${name.title.capitalizeAll()} ${fullName()}"

fun Person.info() = "${location.city.capitalizeAll()}, ${nat.codeToCountry()}"

fun String.codeToCountry(): String = Locale("", this).getDisplayCountry(Locale("en"))

fun Date.formattedWithTime(format: CharSequence = "d. M. yyyy, hh:mm:ss"): CharSequence {
    return DateFormat.format(format, this)
}

fun Date.format(format: CharSequence = "d. M. yyyy"): CharSequence {
    return DateFormat.format(format, this)
}

fun Context.shake(vararg views: View) {
    val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//    if (Build.VERSION.SDK_INT < 26)
    @Suppress("DEPRECATION")
    vibrator.vibrate(SHAKE_DURATION.toLong())
//    else
//        vibrator.vibrate(VibrationEffect.createOneShot(SHAKE_DURATION.toLong(), 1))
    views.forEach { it.shake() }
}

fun View.shake(dur: Int = SHAKE_DURATION, count: Int = SHAKE_COUNT) {
    startAnimation(RotateAnimation(-3f, 3f, (width / 2).toFloat(), (height / 2).toFloat()).apply {
        repeatCount = count
        repeatMode = Animation.REVERSE
        duration = (dur / count).toLong()
    })
}

val TextView?.string: String
    get() = this?.text?.toString() ?: ""

val TextInputLayout.text: String
    get() = editText.string

fun Activity.color(id: Int) = ContextCompat.getColor(this, id)

// Context.getDrawable requires min API 21
fun Context.drawable(id: Int): Drawable = ContextCompat.getDrawable(this, id)!!

/**
 * Inspired (copied) from Anko org.jetbrains.anko.internals
 */
inline fun <reified T : Activity> Activity.startActivity(vararg params: Pair<String, Any?>) {
    val i = Intent(this, T::class.java).applyParams(params)
    startActivity(i)
}

inline fun <reified T : Service> Activity.startService(vararg params: Pair<String, Any?>) {
    val i = Intent(this, T::class.java).applyParams(params)
    startService(i)
}

fun Intent.applyParams(params: Array<out Pair<String, Any?>>) = this.apply {
    params.forEach { (first, value) ->
        when (value) {
            null -> putExtra(first, null as Serializable?)
            is Int -> putExtra(first, value)
            is Long -> putExtra(first, value)
            is CharSequence -> putExtra(first, value)
            is String -> putExtra(first, value)
            is Float -> putExtra(first, value)
            is Double -> putExtra(first, value)
            is Char -> putExtra(first, value)
            is Short -> putExtra(first, value)
            is Boolean -> putExtra(first, value)
            is Serializable -> putExtra(first, value)
            is Bundle -> putExtra(first, value)
            is Parcelable -> putExtra(first, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> putExtra(first, value)
                value.isArrayOf<String>() -> putExtra(first, value)
                value.isArrayOf<Parcelable>() -> putExtra(first, value)
                else -> throw IllegalArgumentException("Wrong type!")
            }
            is IntArray -> putExtra(first, value)
            is LongArray -> putExtra(first, value)
            is FloatArray -> putExtra(first, value)
            is DoubleArray -> putExtra(first, value)
            is CharArray -> putExtra(first, value)
            is ShortArray -> putExtra(first, value)
            is BooleanArray -> putExtra(first, value)
            else -> throw IllegalArgumentException("Wrong type!")
        }
    }
}

fun Activity.startShareActivity(subject: String, text: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, text)
    }
    startActivity(Intent.createChooser(intent, "Share via"))
}

fun Activity.startBrowseActivity(url: String) {
    val browseIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(browseIntent)
}

fun Activity.displayAlert(message: String? = null, op: (AlertDialog.Builder.() -> Unit)? = null) {
    runOnUiThread {
        AlertDialog.Builder(this, R.style.AppTheme_Dialog).apply {
            message?.let { setMessage(it) }
            op?.invoke(this)
        }.show()
    }
}

fun isAppiumTest() = BuildConfig.BUILD_VERSION == "APPIUM"

fun isDebug() = BuildConfig.BUILD_VERSION == "DEBUG"
