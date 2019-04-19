package com.ulj.slicky.kotlinfakesocial.activity.creator

import com.ulj.slicky.kotlinfakesocial.activity.ProgressDialogTask
import com.ulj.slicky.kotlinfakesocial.db.FakeDBHandler

import java.io.IOException
import java.lang.ref.WeakReference

/**
 * Created by SlickyPC on 30.5.2017
 */
internal class CreatorTask(
        activity: CreatorActivity,
        private val content: String
) : ProgressDialogTask<Boolean>(activity, "Uploading Content...") {

    private val activityReference = WeakReference(activity)

    @Throws(IOException::class)
    override fun backgroundTask() = FakeDBHandler.uploadContent(content)

    override fun success(result: Boolean) {
        if (result)
            activityReference.get()?.onCreatingSuccess()
        else
            activityReference.get()?.onCreatingFail("Error occurred while uploading Content to Server!")
    }

    override fun fail(e: Exception) {
        activityReference.get()?.onCreatingFail("Error occurred while uploading Content to Database!", e)
    }

}
