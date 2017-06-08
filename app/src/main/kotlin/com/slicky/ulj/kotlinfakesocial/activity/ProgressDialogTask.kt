package com.slicky.ulj.kotlinfakesocial.activity

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.AppCompatDrawableManager
import com.slicky.ulj.kotlinfakesocial.R
import com.slicky.ulj.kotlinfakesocial.drawable

import java.io.IOException

/**
 * Created by SlickyPC on 21.5.2017
 */
abstract class ProgressDialogTask<T>(context: Context, message: String) {

    private val progress = ProgressDialog(context, R.style.AppTheme_Dialog).apply {
        setMessage(message)
        isIndeterminate = true
        setIndeterminateDrawable(context.drawable(R.drawable.loading_drawable))
        setCancelable(false)
    }

    private val task = object : AsyncTask<Void, Void, T>() {
        override fun onPreExecute() {
            progress.show()
        }

        override fun doInBackground(vararg voids: Void): T? {
            try {
                return backgroundTask()
            } catch (e: Exception) {
                if (!isCancelled) {
                    fail(e)
                    cancel(true)
                }
                return null
            }
        }

        override fun onPostExecute(result: T) {
            progress.dismiss()
            success(result)
        }

        override fun onCancelled() {
            progress.dismiss()
        }
    }

    fun execute() {
        task.execute()
    }

    fun cancel() {
        progress.dismiss()
        task.cancel(true)
    }

    @Throws(IOException::class)
    abstract fun backgroundTask(): T

    abstract fun success(result: T)

    abstract fun fail(e: Exception)
}
