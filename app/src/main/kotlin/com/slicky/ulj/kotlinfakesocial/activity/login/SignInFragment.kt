package com.slicky.ulj.kotlinfakesocial.activity.login

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.widget.EditText
import com.slicky.ulj.kotlinfakesocial.R
import com.slicky.ulj.kotlinfakesocial.activity.content.ContentActivity
import com.slicky.ulj.kotlinfakesocial.shake

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

    private lateinit var validator: SignInValidator
    private var task: SignInTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.signin_fragment, container, false)

        validator = SignInValidator(view)

        emailField = view.findViewById(R.id.signin_email) as EditText
        passwordField = view.findViewById(R.id.signin_password) as EditText

        passwordField.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == IME_ACTION_DONE) {
                trySignin()
                true
            } else {
                false
            }
        }

        // TODO: This should be changed.
        emailField.setText("change@me.pls")
        passwordField.setText("password")

        return view
    }

    override fun onDetach() {
        super.onDetach()
        task?.cancel()
    }

    internal fun trySignin() {
        if (validator.validate()) {
            task = SignInTask(this,
                    emailField.text.toString(),
                    passwordField.text.toString()).apply { execute() }
        } else {
            shakeStage()
        }
    }

    internal fun successSignin() {
        val intent = Intent(context, ContentActivity::class.java)
        startActivity(intent)
        activity.finish()
    }

    internal fun failSignin(text: String, e: Exception?) {
        displayDialog(text + if (e != null) "\n" + e.localizedMessage else "")
        Log.wtf(TAG, text, e)
        shakeStage()
    }

    private fun shakeStage() {
        context.shake(emailField, passwordField)
    }

    private fun displayDialog(text: String) {
        activity.runOnUiThread {
            val builder = AlertDialog.Builder(context, R.style.AppTheme_Dialog)
                    .setMessage(text)
            builder.create().show()
        }
    }
}
