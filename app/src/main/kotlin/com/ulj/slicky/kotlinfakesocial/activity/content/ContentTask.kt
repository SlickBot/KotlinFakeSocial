package com.ulj.slicky.kotlinfakesocial.activity.content

import com.ulj.slicky.kotlinfakesocial.activity.ProgressDialogTask
import com.ulj.slicky.kotlinfakesocial.db.FakeDBHandler
import com.ulj.slicky.kotlinfakesocial.model.content.Content
import java.io.IOException
import java.lang.ref.WeakReference

/**
 * Created by SlickyPC on 21.5.2017
 */
internal class ContentTask(activity: ContentActivity)
    : ProgressDialogTask<List<Content>>(activity, "Loading Content...") {

    val activityReference = WeakReference(activity)

    @Throws(IOException::class)
    override fun backgroundTask() = FakeDBHandler.getContent()

    override fun success(result: List<Content>) = activityReference.get()?.setContent(result)

    override fun fail(e: Exception) = activityReference.get()?.handleError("Could not retrieve Content!", e)
}
