package com.slicky.ulj.kotlinfakesocial.activity.detail

import com.slicky.ulj.kotlinfakesocial.activity.ProgressDialogTask
import com.slicky.ulj.kotlinfakesocial.db.FakeDBHandler
import com.slicky.ulj.kotlinfakesocial.model.content.Content

import java.io.IOException

/**
 * Created by SlickyPC on 8.6.2017
 */
class RemoveTask(private val activity: DetailActivity,
                 private val content: Content)
    : ProgressDialogTask<Unit>(activity, "Removing Content...") {

    @Throws(IOException::class)
    override fun backgroundTask(): Unit {
        return FakeDBHandler.removeContent(content)
    }

    override fun success(result: Unit) {
        activity.finish()
    }

    override fun fail(e: Exception) {
        activity.handleError("Error occurred while removing Content!", e)
    }
}
