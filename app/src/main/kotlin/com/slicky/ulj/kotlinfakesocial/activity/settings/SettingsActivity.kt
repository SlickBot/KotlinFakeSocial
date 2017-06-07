package com.slicky.ulj.kotlinfakesocial.activity.settings

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import com.slicky.ulj.kotlinfakesocial.*
import com.slicky.ulj.kotlinfakesocial.activity.BackableActivity
import com.slicky.ulj.kotlinfakesocial.activity.service.NotifyingService

/**
 * Created by SlickyPC on 22.5.2017
 */
class SettingsActivity : BackableActivity() {

    private val onOffSwitch by findView<Switch>(R.id.settings_on_off_switch)
    private val durationField by findView<EditText>(R.id.settings_duration_field)
    private val durationText by findView<TextView>(R.id.settings_duration_text)
    private val randomSwitch by findView<Switch>(R.id.settings_random_switch)

    private lateinit var prefs: FakePreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        prefs = FakePreferences(this)

        onOffSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.isNotifyOn = isChecked
            updateFields()
            if (isChecked)
                startService<NotifyingService>()
        }

        durationField.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                prefs.notifyDuration = durationField.string.toInt()
                updateFields()
            }
            false
        }

        randomSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.isNotifyRandom = isChecked
            updateFields()
        }

        updateFields()
    }

    private fun updateFields() {
        val onOff = prefs.isNotifyOn
        val duration = prefs.notifyDuration
        val random = prefs.isNotifyRandom

        onOffSwitch.isChecked = onOff
        durationField.setText(duration.toString())
        randomSwitch.isChecked = random

        val colorId = if (random) R.color.colorTextDark else R.color.colorText
        val color = ContextCompat.getColor(this, colorId)

        durationText.setTextColor(color)
        durationField.setTextColor(color)
        durationField.isEnabled = !random
    }
}
