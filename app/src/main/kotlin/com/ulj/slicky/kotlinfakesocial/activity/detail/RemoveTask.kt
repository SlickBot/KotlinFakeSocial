package com.ulj.slicky.kotlinfakesocial.activity.detail

import com.ulj.slicky.kotlinfakesocial.activity.ProgressDialogTask
import com.ulj.slicky.kotlinfakesocial.db.FakeDBHandler
import com.ulj.slicky.kotlinfakesocial.model.content.Content

import java.io.IOException
import java.lang.ref.WeakReference

/**
 * Created by SlickyPC on 8.6.2017
 */
internal class RemoveTask(activity: DetailActivity,
                          private val content: Content)
    : ProgressDialogTask<Unit>(activity, "Removing Content...") {

    private val activityReference = WeakReference(activity)

    @Throws(IOException::class)
    override fun backgroundTask() = FakeDBHandler.removeContent(content)

    override fun success(result: Unit) = activityReference.get()?.finish()

    override fun fail(e: Exception) = activityReference.get()?.handleError("Error occurred while removing Content!", e)
}
