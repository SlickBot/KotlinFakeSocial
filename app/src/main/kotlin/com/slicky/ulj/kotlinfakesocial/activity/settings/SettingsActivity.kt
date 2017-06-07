package com.slicky.ulj.kotlinfakesocial.activity.settings

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import com.slicky.ulj.kotlinfakesocial.FakePreferences
import com.slicky.ulj.kotlinfakesocial.R
import com.slicky.ulj.kotlinfakesocial.activity.BackableActivity
import com.slicky.ulj.kotlinfakesocial.activity.service.NotifyingService
import com.slicky.ulj.kotlinfakesocial.findView
import java.util.*

/**
 * Created by SlickyPC on 22.5.2017
 */
class SettingsActivity : BackableActivity() {

    private lateinit var prefs: FakePreferences

    private val onOffSwitch by findView<Switch>(R.id.settings_on_off_switch)
    private val durationField by findView<EditText>(R.id.settings_duration_field)
    private val durationText by findView<TextView>(R.id.settings_duration_text)
    private val randomSwitch by findView<Switch>(R.id.settings_random_switch)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        prefs = FakePreferences(this)

        onOffSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.isNotifyOn = isChecked
            updateFields()
            if (isChecked)
                startNotifyService()
        }

        durationField.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val duration = Integer.parseInt(durationField.text.toString())
                prefs.notifyDuration = duration
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

    private fun startNotifyService() {
        val intent = Intent(this@SettingsActivity, NotifyingService::class.java)
        startService(intent)
    }

    private fun updateFields() {
        val onOff = prefs.isNotifyOn
        val duration = prefs.notifyDuration
        val random = prefs.isNotifyRandom

        onOffSwitch.isChecked = onOff
        durationField.setText(String.format(Locale.getDefault(), "%d", duration))
        randomSwitch.isChecked = random

        val id = if (random) R.color.colorTextDark else R.color.colorText
        val color = ContextCompat.getColor(this, id)

        durationText.setTextColor(color)
        durationField.setTextColor(color)
        durationField.isEnabled = !random
    }
}
