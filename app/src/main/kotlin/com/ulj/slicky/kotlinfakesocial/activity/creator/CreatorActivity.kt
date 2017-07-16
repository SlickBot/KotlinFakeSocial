package com.ulj.slicky.kotlinfakesocial.activity.creator

import android.os.Bundle
import android.util.Log
import android.view.View
import com.ulj.slicky.kotlinfakesocial.R
import com.ulj.slicky.kotlinfakesocial.activity.BackableActivity
import com.ulj.slicky.kotlinfakesocial.displayAlert
import com.ulj.slicky.kotlinfakesocial.string
import kotlinx.android.synthetic.main.creator_activity.creator_text as textField


/**
 * Created by SlickyPC on 30.5.2017
 */
class CreatorActivity : BackableActivity() {

    companion object {
        private val TAG = CreatorActivity::class.java.canonicalName
    }

    private var task: CreatorTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.creator_activity)
    }

    override fun onStop() {
        super.onStop()
        task?.cancel()
    }

    @Suppress("UNUSED_PARAMETER")
    fun onNewContent(view: View) {
        val validator = CreatorValidator(this)
        if (validator.validate())
            task = CreatorTask(this, textField.string).apply { execute() }
    }

    internal fun onCreatingSuccess() {
        displayAlert("Successfully uploaded new Content!") {
            setCancelable(false)
            setPositiveButton("Cool!", { _, _ -> finish() })
        }
    }

    internal fun onCreatingFail(text: String, e: Exception? = null) {
        displayAlert(text + if (e != null) "\n" + e.localizedMessage else "")
        Log.wtf(TAG, text, e)
    }
}
