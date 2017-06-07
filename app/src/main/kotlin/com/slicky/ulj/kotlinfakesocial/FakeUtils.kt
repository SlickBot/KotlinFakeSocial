@file:Suppress("unused")

package com.slicky.ulj.kotlinfakesocial

import android.app.Activity
import android.content.Context
import android.os.Vibrator
import android.text.format.DateFormat
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.slicky.ulj.kotlinfakesocial.model.person.Person
import java.util.*

/**
 * Created by SlickyPC on 24.5.2017
 */
const val SHAKE_DURATION = 300
const val SHAKE_COUNT = 10

fun String.capitalizeAll() = split(" ").map(String::capitalize).joinToString(separator = " ")

fun Person.fullName() = "${name.first.capitalizeAll()} ${name.last.capitalizeAll()}"

fun Person.fullNameWithTitle() = "${name.title} ${fullName()}"

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
    vibrator.vibrate(SHAKE_DURATION.toLong())
    views.forEach { it.shake() }
}

fun View.shake(dur: Int = SHAKE_DURATION, count: Int = SHAKE_COUNT) {
    startAnimation(RotateAnimation(-3f, 3f, (width / 2).toFloat(), (height / 2).toFloat()).apply {
        repeatCount = count
        repeatMode = Animation.REVERSE
        duration = (dur / count).toLong()
    })
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T : View> Activity.findView(id: Int) = lazy {
    findViewById(id)?.let {
        when (it) {
            is T -> it
            else -> throw TypeCastException("Could not cast View to ${T::class}")
        }
    } ?: throw IllegalArgumentException("Could not find ${T::class} with id = $id")
}
