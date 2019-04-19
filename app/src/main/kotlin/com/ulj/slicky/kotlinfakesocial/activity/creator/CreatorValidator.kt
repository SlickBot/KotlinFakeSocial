package com.ulj.slicky.kotlinfakesocial.activity.creator

import com.ulj.slicky.kotlinfakesocial.string
import kotlinx.android.synthetic.main.creator_activity.creator_text_layout as textLayout

/**
 * Created by SlickyPC on 8.6.2017
 */
internal class CreatorValidator(val activity: CreatorActivity) {

    fun validate(): Boolean {
        with(activity) {
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
        }

        return true
    }

}
