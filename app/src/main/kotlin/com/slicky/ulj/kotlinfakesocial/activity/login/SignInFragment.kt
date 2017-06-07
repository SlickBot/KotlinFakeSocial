package com.slicky.ulj.kotlinfakesocial.activity.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
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
class SignInFragment : Fragment() {

    companion object {
        private val TAG = SignInFragment::class.java.canonicalName

        internal fun newInstance(): SignInFragment {
            return SignInFragment()
        }
    }

    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText

    private var task: SignInTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.signin_fragment, container, false).apply {

            emailField = findViewById(R.id.signin_email) as EditText
            passwordField = findViewById(R.id.signin_password) as EditText

            passwordField.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == IME_ACTION_DONE) {
                    trySignin()
                    true
                } else {
                    false
                }
            }

            val signInButton = findViewById(R.id.signin_signin_button) as Button
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
            context.shake(emailField, passwordField)
    }

    internal fun successSignin() {
        activity.startActivity<ContentActivity>()
        activity.finish()
    }

    internal fun failSignin(text: String, e: Exception?) {
        displayDialog(text + if (e != null) "\n" + e.localizedMessage else "")
        context.shake(emailField, passwordField)
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
