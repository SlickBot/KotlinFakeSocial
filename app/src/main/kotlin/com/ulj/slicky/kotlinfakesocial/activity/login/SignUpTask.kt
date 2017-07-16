package com.ulj.slicky.kotlinfakesocial.activity.login

import com.ulj.slicky.kotlinfakesocial.activity.ProgressDialogTask
import com.ulj.slicky.kotlinfakesocial.db.FakeDBHandler

import java.io.IOException

/**
 * Created by SlickyPC on 21.5.2017
 */
internal class SignUpTask(private val fragment: SignUpFragment,
                          private val first: String,
                          private val last: String,
                          private val email: String,
                          private val password: String)
    : ProgressDialogTask<Boolean>(fragment.context, "Signing Up...") {

    @Throws(IOException::class)
    override fun backgroundTask() = FakeDBHandler.signup(first, last, email, password)

    override fun success(result: Boolean) {
        if (result)
            fragment.successSignup()
        else
            fragment.failSignup("Could not create new account!", null)
    }

    override fun fail(e: Exception) = fragment.failSignup("Could not Sign Up!", e)
}
