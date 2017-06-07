package com.slicky.ulj.kotlinfakesocial.activity

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.AppCompatDrawableManager
import com.slicky.ulj.kotlinfakesocial.R

import java.io.IOException

/**
 * Created by SlickyPC on 21.5.2017
 */
abstract class ProgressDialogTask<T>(context: Context, message: String) {

    private val progress: ProgressDialog
    private val task: AsyncTask<Void, Void, T>

    init {
        val logo = AppCompatDrawableManager.get().getDrawable(context, R.drawable.loading_drawable)
        progress = ProgressDialog(context, R.style.AppTheme_Dialog).apply {
            setMessage(message)
            isIndeterminate = true
            setIndeterminateDrawable(logo)
            setCancelable(false)
        }

        task = object : AsyncTask<Void, Void, T>() {
            override fun onPreExecute() {
                progress.show()
            }

            override fun doInBackground(vararg voids: Void): T? {
                try {
                    return backgroundTask()
                } catch (e: Exception) {
                    if (!isCancelled()) {
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
