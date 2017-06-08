package com.slicky.ulj.kotlinfakesocial.activity.login

import android.support.design.widget.TextInputLayout
import android.util.Patterns
import android.view.View
import com.slicky.ulj.kotlinfakesocial.R
import com.slicky.ulj.kotlinfakesocial.string

/**
 * Created by SlickyPC on 1.6.2017
 */
internal class SignInValidator(view: View) {

    private val emailLayout = view.findViewById(R.id.signin_email_layout) as TextInputLayout
    private val passwordLayout = view.findViewById(R.id.signin_password_layout) as TextInputLayout

    fun validate(): Boolean {
        emailLayout.error = null
        passwordLayout.error = null

        var errorField: View? = null

        val email = emailLayout.editText.string
        val password = passwordLayout.editText.string

        if (email.length < 5) {
            emailLayout.error = "Email is too short! (min 5)"
            errorField = emailLayout
        } else if (email.length > 50) {
            emailLayout.error = "Email is too long! (max 50)"
            errorField = emailLayout
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.error = "Email is not valid!"
            if (errorField == null)
                errorField = emailLayout
        }

        if (password.length < 8) {
            passwordLayout.error = "Password is too short! (min 8)"
            if (errorField == null)
                errorField = passwordLayout
        } else if (password.length > 50) {
            passwordLayout.error = "Password is too long! (max 50)"
            if (errorField == null)
                errorField = passwordLayout
        }

        if (errorField != null) {
            errorField.requestFocus()
            return false
        }

        return true
    }
}
