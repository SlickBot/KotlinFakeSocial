package com.slicky.ulj.kotlinfakesocial.activity.login

import android.support.design.widget.TextInputLayout
import android.util.Patterns
import android.view.View
import android.widget.CheckBox
import com.slicky.ulj.kotlinfakesocial.R

/**
 * Created by SlickyPC on 1.6.2017
 */
internal class SignUpValidator(view: View) {

    private val firstLayout = view.findViewById(R.id.signup_first_name_layout) as TextInputLayout
    private val lastLayout = view.findViewById(R.id.signup_last_name_layout) as TextInputLayout
    private val emailLayout = view.findViewById(R.id.signup_email_layout) as TextInputLayout
    private val firstPasswordLayout = view.findViewById(R.id.signup_first_password_layout) as TextInputLayout
    private val secondPasswordLayout = view.findViewById(R.id.signup_second_password_layout) as TextInputLayout

    private val firstField = firstLayout.editText
    private val lastField = lastLayout.editText
    private val emailField = emailLayout.editText
    private val firstPasswordField = firstPasswordLayout.editText
    private val secondPasswordField = secondPasswordLayout.editText

    private val legalCheckBox = view.findViewById(R.id.legal_checkbox) as CheckBox

    fun validate(): Boolean {
        var errorField: View? = null

        firstLayout.error = null
        lastLayout.error = null
        emailLayout.error = null
        firstPasswordLayout.error = null
        secondPasswordLayout.error = null

        val first = firstField?.text.toString()
        val last = lastField?.text.toString()
        val email = emailField?.text.toString()
        val firstPassword = firstPasswordField?.text.toString()
        val secondPassword = secondPasswordField?.text.toString()

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

    fun acceptedLegalNotice(): Boolean {
        return legalCheckBox.isChecked
    }
}
