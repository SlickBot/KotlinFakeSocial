package com.slicky.ulj.kotlinfakesocial.activity.creator

import com.slicky.ulj.kotlinfakesocial.activity.ProgressDialogTask
import com.slicky.ulj.kotlinfakesocial.db.FakeDBHandler

import java.io.IOException

/**
 * Created by SlickyPC on 30.5.2017
 */
internal class CreatorTask(private val activity: CreatorActivity,
                           private val content: String)
    : ProgressDialogTask<Boolean>(activity, "Uploading Content...") {

    @Throws(IOException::class)
    override fun backgroundTask(): Boolean {
        return FakeDBHandler.uploadContent(content)
    }

    override fun success(result: Boolean) {
        if (result)
            activity.onCreatingSuccess()
        else
            activity.onCreatingFail("Error occurred while uploading Content to Server!")
    }

    override fun fail(e: Exception) {
        activity.onCreatingFail("Error occurred while uploading Content to Database!", e)
    }
}

