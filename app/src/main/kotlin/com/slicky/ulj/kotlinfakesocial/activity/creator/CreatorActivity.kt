package com.slicky.ulj.kotlinfakesocial.activity.creator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.slicky.ulj.kotlinfakesocial.R
import com.slicky.ulj.kotlinfakesocial.activity.BackableActivity
import com.slicky.ulj.kotlinfakesocial.displayAlert
import com.slicky.ulj.kotlinfakesocial.findView
import com.slicky.ulj.kotlinfakesocial.string

/**
 * Created by SlickyPC on 30.5.2017
 */
class CreatorActivity : BackableActivity() {

    companion object {
        private val TAG = CreatorActivity::class.java.canonicalName
    }

    private val textField by findView<EditText>(R.id.creator_text)
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
