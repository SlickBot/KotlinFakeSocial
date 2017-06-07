package com.slicky.ulj.kotlinfakesocial.activity.content

import com.slicky.ulj.kotlinfakesocial.activity.ProgressDialogTask
import com.slicky.ulj.kotlinfakesocial.db.DummyDBHandler
import com.slicky.ulj.kotlinfakesocial.model.content.Content
import java.io.IOException

/**
 * Created by SlickyPC on 21.5.2017
 */
internal class ContentTask(private val activity: ContentActivity
) : ProgressDialogTask<List<Content>>(activity, "Loading Content...") {

    @Throws(IOException::class)
    override fun backgroundTask(): List<Content> {
        return DummyDBHandler.getContent()
    }

    override fun success(result: List<Content>) {
        activity.setContent(result)
    }

    override fun fail(e: Exception) {
        activity.handleError("Could not retrieve Content!", e)
    }
}
