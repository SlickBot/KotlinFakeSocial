package com.slicky.ulj.kotlinfakesocial.activity.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.slicky.ulj.kotlinfakesocial.R
import com.slicky.ulj.kotlinfakesocial.activity.content.ContentActivity
import com.slicky.ulj.kotlinfakesocial.shake
import com.slicky.ulj.kotlinfakesocial.startActivity
import com.slicky.ulj.kotlinfakesocial.string

/**
 * Created by SlickyPC on 18.5.2017
 */
class SignUpFragment : Fragment() {

    companion object {
        private val TAG = SignUpFragment::class.java.canonicalName

        internal fun newInstance(): SignUpFragment {
            return SignUpFragment()
        }
    }

    private lateinit var firstField: EditText
    private lateinit var lastField: EditText
    private lateinit var emailField: EditText
    private lateinit var firstPasswordField: EditText
    private lateinit var secondPasswordField: EditText

    private var task: SignUpTask? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.signup_fragment, container, false).apply {
            firstField = findViewById(R.id.signup_first_name) as EditText
            lastField = findViewById(R.id.signup_last_name) as EditText
            emailField = findViewById(R.id.signup_email) as EditText
            firstPasswordField = findViewById(R.id.signup_first_password) as EditText
            secondPasswordField = findViewById(R.id.signup_second_password) as EditText

            val signUpButton = findViewById(R.id.signup_signup_button) as Button
            signUpButton.setOnClickListener {
                trySignup()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        task?.cancel()
    }

    internal fun trySignup() {
        val validator = SignUpValidator(view ?: return)
        if (validator.validate()) {
            if (validator.acceptedLegalNotice) {
                task = SignUpTask(this,
                        firstField.string,
                        lastField.string,
                        emailField.string,
                        firstPasswordField.string).apply { execute() }
            } else {
                displayDialog("You have to accept legal notice!")
            }
        } else {
            context.shake(firstField, lastField, emailField, firstPasswordField, secondPasswordField)
        }
    }

    internal fun successSignup() {
        activity.startActivity<ContentActivity>()
        activity.finish()
    }

    internal fun failSignup(text: String, e: Exception?) {
        displayDialog(text + if (e != null) "\n" + e.localizedMessage else "")
        context.shake(firstField, lastField, emailField, firstPasswordField, secondPasswordField)
        Log.wtf(TAG, text, e)
    }

    private fun displayDialog(text: String) {
        activity.runOnUiThread {
            val builder = AlertDialog.Builder(context, R.style.AppTheme_Dialog)
                    .setMessage(text)
            builder.create().show()
        }
    }
}
