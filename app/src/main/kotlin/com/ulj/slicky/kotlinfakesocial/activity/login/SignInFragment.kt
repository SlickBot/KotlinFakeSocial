package com.ulj.slicky.kotlinfakesocial.activity.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.widget.Button
import android.widget.EditText
import com.ulj.slicky.kotlinfakesocial.*
import com.ulj.slicky.kotlinfakesocial.activity.content.ContentActivity

/**
 * Created by SlickyPC on 18.5.2017
 */
class SignInFragment : Fragment() {

    companion object {
        private val TAG = SignInFragment::class.java.canonicalName

        internal fun newInstance() = SignInFragment()
    }

    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText

    private var task: SignInTask? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.signin_fragment, container, false).apply {

            emailField = findViewById<EditText>(R.id.signin_email)
            passwordField = findViewById<EditText>(R.id.signin_password)

            passwordField.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == IME_ACTION_DONE) {
                    trySignin()
                    true
                } else {
                    false
                }
            }

            val signInButton = findViewById<Button>(R.id.signin_signin_button)
            signInButton.setOnClickListener {
                trySignin()
            }

            // TODO: This should be changed.
            emailField.setText("change@me.pls")
            passwordField.setText("password")
        }
    }

    override fun onDetach() {
        super.onDetach()
        task?.cancel()
    }

    internal fun trySignin() {
        val validator = SignInValidator(view ?: return)
        if (validator.validate())
            task = SignInTask(this, emailField.string, passwordField.string).apply { execute() }
        else
            shakeStage()
    }

    internal fun successSignin() {
        activity.startActivity<ContentActivity>()
        activity.finish()
    }

    internal fun failSignin(text: String, e: Exception?) {
        activity.displayAlert(text + if (e != null) "\n" + e.localizedMessage else "")
        shakeStage()
        Log.wtf(TAG, text, e)
    }

    private fun shakeStage() = context.shake(emailField, passwordField)
}