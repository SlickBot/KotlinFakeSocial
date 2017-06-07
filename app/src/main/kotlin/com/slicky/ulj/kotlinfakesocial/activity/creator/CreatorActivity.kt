package com.slicky.ulj.kotlinfakesocial.activity.creator

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.EditText
import com.slicky.ulj.kotlinfakesocial.R
import com.slicky.ulj.kotlinfakesocial.activity.BackableActivity
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
        if (validate())
            task = CreatorTask(this, textField.string).apply { execute() }
    }

    private fun validate(): Boolean {
        textField.error = null

        val text = textField.string
        if (text.length < 5) {
            textField.error = "Text is too short! (minimum is 5)"
            textField.requestFocus()
            return false
        }
        if (text.length > 256) {
            textField.error = "Text is too long! (minimum is 256)"
            textField.requestFocus()
            return false
        }
        return true
    }

    internal fun onCreatingSuccess() {
        displayDialog("Successfully uploaded new Content!")
    }

    internal fun onCreatingFail(text: String, e: Exception? = null) {
        displayDialog(text + if (e != null) "\n" + e.localizedMessage else "")
        Log.wtf(TAG, text, e)
    }

    private fun displayDialog(text: String) {
        runOnUiThread {
            val builder = AlertDialog.Builder(this@CreatorActivity, R.style.AppTheme_Dialog)
                    .setMessage(text)
                    .setCancelable(false)
                    .setPositiveButton("Cool!", { _, _ -> finish() })
            builder.create().show()
        }
    }
}
