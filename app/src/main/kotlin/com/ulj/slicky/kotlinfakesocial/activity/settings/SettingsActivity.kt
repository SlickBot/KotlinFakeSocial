package com.ulj.slicky.kotlinfakesocial.activity.settings

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.ulj.slicky.kotlinfakesocial.*
import com.ulj.slicky.kotlinfakesocial.activity.BackableActivity
import com.ulj.slicky.kotlinfakesocial.activity.service.NotifyingService
import kotlinx.android.synthetic.main.settings_activity.settings_duration_field as durationField
import kotlinx.android.synthetic.main.settings_activity.settings_duration_text as durationText
import kotlinx.android.synthetic.main.settings_activity.settings_on_off_switch as onOffSwitch
import kotlinx.android.synthetic.main.settings_activity.settings_random_switch as randomSwitch

/**
 * Created by SlickyPC on 22.5.2017
 */
class SettingsActivity : BackableActivity() {

    private lateinit var prefs: FakePreferences

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
