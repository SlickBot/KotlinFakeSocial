package com.slicky.ulj.kotlinfakesocial.activity.content

import com.slicky.ulj.kotlinfakesocial.activity.ProgressDialogTask
import com.slicky.ulj.kotlinfakesocial.activity.profile.ProfileActivity.Companion.startOwnerProfile
import com.slicky.ulj.kotlinfakesocial.db.FakeDBHandler
import com.slicky.ulj.kotlinfakesocial.model.person.Person
import java.io.IOException

/**
 * Created by SlickyPC on 23.5.2017
 */
internal class UserTask(private val activity: ContentActivity
) : ProgressDialogTask<Person>(activity, "Loading User...") {

    @Throws(IOException::class)
    override fun backgroundTask() = FakeDBHandler.getUser()

    override fun success(result: Person) = activity.startOwnerProfile(result)

    override fun fail(e: Exception) = activity.handleError("Could not load User data!", e)
}
