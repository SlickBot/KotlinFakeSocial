package com.slicky.ulj.kotlinfakesocial.activity.creator

import android.support.design.widget.TextInputLayout
import com.slicky.ulj.kotlinfakesocial.R
import com.slicky.ulj.kotlinfakesocial.findView
import com.slicky.ulj.kotlinfakesocial.string

/**
 * Created by SlickyPC on 8.6.2017
 */
internal class CreatorValidator(activity: CreatorActivity) {

    private val textLayout  by activity.findView<TextInputLayout>(R.id.creator_text_layout)

    fun validate(): Boolean {
        textLayout.error = null

        val text = textLayout.editText.string
        if (text.length < 5) {
            textLayout.error = "Text is too short! (minimum is 5)"
            textLayout.requestFocus()
            return false
        }
        if (text.length > 256) {
            textLayout.error = "Text is too long! (minimum is 256)"
            textLayout.requestFocus()
            return false
        }

        return true
    }
}
