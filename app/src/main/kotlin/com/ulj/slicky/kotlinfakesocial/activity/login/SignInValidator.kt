package com.ulj.slicky.kotlinfakesocial.activity.login

import android.util.Patterns
import android.view.View
import com.ulj.slicky.kotlinfakesocial.string
import kotlinx.android.synthetic.main.signin_fragment.view.*

/**
 * Created by SlickyPC on 1.6.2017
 */
internal class SignInValidator(view: View) {

    private val emailLayout = view.signin_email_layout
    private val passwordLayout = view.signin_password_layout

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
