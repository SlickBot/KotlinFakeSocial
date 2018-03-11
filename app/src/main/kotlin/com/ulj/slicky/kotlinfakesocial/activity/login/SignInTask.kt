package com.ulj.slicky.kotlinfakesocial.activity.login

import com.ulj.slicky.kotlinfakesocial.activity.ProgressDialogTask
import com.ulj.slicky.kotlinfakesocial.db.FakeDBHandler

import java.io.IOException

/**
 * Created by SlickyPC on 21.5.2017
 */
internal class SignInTask(private val fragment: SignInFragment,
                          private val email: String,
                          private val password: String)
    : ProgressDialogTask<Boolean>(fragment.requireContext(), "Signing In...") {

    @Throws(IOException::class)
    override fun backgroundTask(): Boolean = FakeDBHandler.signin(email, password)

    override fun success(result: Boolean) {
        if (result)
            fragment.successSignin()
        else
            fragment.failSignin("Invalid User data!", null)
    }

    override fun fail(e: Exception) = fragment.failSignin("Could not Sign In!", e)
}
