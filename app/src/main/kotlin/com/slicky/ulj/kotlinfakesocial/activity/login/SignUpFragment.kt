package com.slicky.ulj.kotlinfakesocial.activity.login

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.slicky.ulj.kotlinfakesocial.R
import com.slicky.ulj.kotlinfakesocial.activity.content.ContentActivity
import com.slicky.ulj.kotlinfakesocial.shake

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

    private lateinit var validator: SignUpValidator
    private var task: SignUpTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.signup_fragment, container, false).apply {
            validator = SignUpValidator(this)

            firstField = findViewById(R.id.signup_first_name) as EditText
            lastField = findViewById(R.id.signup_last_name) as EditText
            emailField = findViewById(R.id.signup_email) as EditText
            firstPasswordField = findViewById(R.id.signup_first_password) as EditText
            secondPasswordField = findViewById(R.id.signup_second_password) as EditText
        }
    }

    override fun onDetach() {
        super.onDetach()
        task?.cancel()
    }

    internal fun trySignup() {
        if (validator.validate()) {
            if (validator.acceptedLegalNotice()) {
                task = SignUpTask(this,
                        firstField.text.toString(),
                        lastField.text.toString(),
                        emailField.text.toString(),
                        firstPasswordField.text.toString()).apply { execute() }
            } else {
                displayDialog("You have to accept legal notice!")
            }
        } else {
            shakeStage()
        }
    }

    internal fun successSignup() {
        val intent = Intent(activity, ContentActivity::class.java)
        startActivity(intent)
        activity.finish()
    }

    internal fun failSignup(text: String, e: Exception?) {
        displayDialog(text + if (e != null) "\n" + e.localizedMessage else "")
        Log.wtf(TAG, text, e)
        shakeStage()
    }

    private fun shakeStage() {
        context.shake(firstField, lastField, emailField, firstPasswordField, secondPasswordField)
    }

    private fun displayDialog(text: String) {
        activity.runOnUiThread {
            val builder = AlertDialog.Builder(context, R.style.AppTheme_Dialog)
                    .setMessage(text)
            builder.create().show()
        }
    }
}
