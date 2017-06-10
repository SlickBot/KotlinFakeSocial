package com.slicky.ulj.kotlinfakesocial.activity.settings

import android.os.Bundle
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

    private lateinit var prefs: FakePreferences

    private val onOffSwitch     by findView<Switch>(R.id.settings_on_off_switch)
    private val durationField   by findView<EditText>(R.id.settings_duration_field)
    private val durationText    by findView<TextView>(R.id.settings_duration_text)
    private val randomSwitch    by findView<Switch>(R.id.settings_random_switch)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        prefs = FakePreferences(this).apply {
            onOffSwitch.setOnCheckedChangeListener { _, isChecked ->
                isNotifyOn = isChecked
                updateFields()
                if (isChecked)
                    startService<NotifyingService>()
            }

            durationField.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    notifyDuration = durationField.string.toInt()
                    updateFields()
                }
                return@setOnEditorActionListener false
            }

            randomSwitch.setOnCheckedChangeListener { _, isChecked ->
                isNotifyRandom = isChecked
                updateFields()
            }
        }

        updateFields()
    }

    private fun updateFields() {
        with(prefs) {
            onOffSwitch.isChecked = isNotifyOn
            durationField.setText(notifyDuration.toString())
            randomSwitch.isChecked = isNotifyRandom

            val colorId = if (isNotifyRandom) R.color.colorTextDark else R.color.colorText
            val color = color(colorId)

            durationText.setTextColor(color)
            durationField.setTextColor(color)
            durationField.isEnabled = !isNotifyRandom
        }
    }
}
