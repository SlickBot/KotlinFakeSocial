package com.ulj.slicky.kotlinfakesocial.activity.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.ulj.slicky.kotlinfakesocial.*
import com.ulj.slicky.kotlinfakesocial.activity.content.ContentActivity
import kotlinx.android.synthetic.main.signup_fragment.view.*

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
            firstField = signup_first_name
            lastField = signup_last_name
            emailField = signup_email
            firstPasswordField = signup_first_password
            secondPasswordField = signup_second_password

            val signUpButton = signup_signup_button
            signUpButton.setOnClickListener {
                trySignup()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        task?.cancel()
    }

    private fun trySignup() {
        val validator = SignUpValidator(view ?: return)
        if (validator.validate()) {
            if (validator.acceptedLegalNotice) {
                task = SignUpTask(this,
                        firstField.string,
                        lastField.string,
                        emailField.string,
                        firstPasswordField.string).apply { execute() }
            } else {
                requireActivity().displayAlert("You have to accept legal notice!")
            }
        } else {
            shakeStage()
        }
    }

    internal fun successSignup() {
        requireActivity().startActivity<ContentActivity>()
        requireActivity().finish()
    }

    internal fun failSignup(text: String, e: Exception?) {
        requireActivity().displayAlert(text + if (e != null) "\n" + e.localizedMessage else "")
        shakeStage()
        Log.wtf(TAG, text, e)
    }

    private fun shakeStage() {
        requireContext().shake(firstField, lastField, emailField, firstPasswordField, secondPasswordField)
    }
}
