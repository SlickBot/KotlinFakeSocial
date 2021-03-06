package com.ulj.slicky.kotlinfakesocial.activity.login

import android.util.Patterns
import android.view.View
import com.ulj.slicky.kotlinfakesocial.text
import kotlinx.android.synthetic.main.signup_fragment.view.*

/**
 * Created by SlickyPC on 1.6.2017
 */
internal class SignUpValidator(view: View) {

    private val firstLayout = view.signup_first_name_layout
    private val lastLayout = view.signup_last_name_layout
    private val emailLayout = view.signup_email_layout
    private val firstPasswordLayout = view.signup_first_password_layout
    private val secondPasswordLayout = view.signup_second_password_layout

    private val legalCheckBox = view.signup_legal_checkbox

    internal val acceptedLegalNotice: Boolean
        get() = legalCheckBox.isChecked

    fun validate(): Boolean {
        var errorField: View? = null

        firstLayout.error = null
        lastLayout.error = null
        emailLayout.error = null
        firstPasswordLayout.error = null
        secondPasswordLayout.error = null

        val first = firstLayout.text
        val last = lastLayout.text
        val email = emailLayout.text
        val firstPassword = firstPasswordLayout.text
        val secondPassword = secondPasswordLayout.text

        if (first.length < 2) {
            firstLayout.error = "First name is too short! (min 2)"
            errorField = firstLayout
        } else if (first.length > 50) {
            firstLayout.error = "First name is too long! (max 50)"
            errorField = firstLayout
        }

        if (last.length < 2) {
            lastLayout.error = "Last name is too short! (min 2)"
            if (errorField == null)
                errorField = lastLayout
        } else if (last.length > 50) {
            lastLayout.error = "Last name is too long! (max 50)"
            if (errorField == null)
                errorField = lastLayout
        }

        if (email.length < 5) {
            emailLayout.error = "Email is too short! (min 5)"
            if (errorField == null)
                errorField = emailLayout
        } else if (email.length > 50) {
            emailLayout.error = "Email is too long! (max 50)"
            if (errorField == null)
                errorField = emailLayout
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.error = "Email is not valid!"
            if (errorField == null)
                errorField = emailLayout
        }

        if (firstPassword.length < 8) {
            firstPasswordLayout.error = "Password is too short! (min 8)"
            if (errorField == null)
                errorField = firstPasswordLayout
        } else if (firstPassword.length > 50) {
            firstPasswordLayout.error = "Password is too long! (max 50)"
            if (errorField == null)
                errorField = firstPasswordLayout
        } else if (secondPassword != firstPassword) {
            secondPasswordLayout.error = "Passwords do not match!"
            if (errorField == null)
                errorField = secondPasswordLayout
        }

        if (errorField != null) {
            errorField.requestFocus()
            return false
        }

        return true
    }

}
