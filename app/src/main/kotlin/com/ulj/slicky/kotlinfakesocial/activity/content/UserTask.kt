package com.ulj.slicky.kotlinfakesocial.activity.content

import com.ulj.slicky.kotlinfakesocial.activity.ProgressDialogTask
import com.ulj.slicky.kotlinfakesocial.activity.profile.ProfileActivity.Companion.startOwnerProfile
import com.ulj.slicky.kotlinfakesocial.db.FakeDBHandler
import com.ulj.slicky.kotlinfakesocial.model.person.Person
import java.io.IOException
import java.lang.ref.WeakReference

/**
 * Created by SlickyPC on 23.5.2017
 */
internal class UserTask(activity: ContentActivity)
    : ProgressDialogTask<Person>(activity, "Loading User...") {

    private val activityReference = WeakReference(activity)

    @Throws(IOException::class)
    override fun backgroundTask() = FakeDBHandler.getUser()

    override fun success(result: Person) = activityReference.get()?.startOwnerProfile(result)

    override fun fail(e: Exception) = activityReference.get()?.handleError("Could not load User data!", e)
}
