package com.ulj.slicky.kotlinfakesocial.activity.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.ulj.slicky.kotlinfakesocial.*
import com.ulj.slicky.kotlinfakesocial.activity.content.ContentActivity

/**
 * Created by SlickyPC on 18.5.2017
 */
class SignUpFragment : Fragment() {

    companion object {
        private val TAG = SignUpFragment::class.java.canonicalName

        internal fun newInstance() = SignUpFragment()
    }

    private lateinit var firstField: EditText
    private lateinit var lastField: EditText
    private lateinit var emailField: EditText
    private lateinit var firstPasswordField: EditText
    private lateinit var secondPasswordField: EditText

    private var task: SignUpTask? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.signup_fragment, container, false).apply {
            firstField = findViewById<EditText>(R.id.signup_first_name)
            lastField = findViewById<EditText>(R.id.signup_last_name)
            emailField = findViewById<EditText>(R.id.signup_email)
            firstPasswordField = findViewById<EditText>(R.id.signup_first_password)
            secondPasswordField = findViewById<EditText>(R.id.signup_second_password)

            val signUpButton = findViewById<Button>(R.id.signup_signup_button)
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
                activity.displayAlert("You have to accept legal notice!")
            }
        } else {
            shakeStage()
        }
    }

    internal fun successSignup() {
        activity.startActivity<ContentActivity>()
        activity.finish()
    }

    internal fun failSignup(text: String, e: Exception?) {
        activity.displayAlert(text + if (e != null) "\n" + e.localizedMessage else "")
        shakeStage()
        Log.wtf(TAG, text, e)
    }

    private fun shakeStage() {
        context.shake(firstField, lastField, emailField, firstPasswordField, secondPasswordField)
    }
}
