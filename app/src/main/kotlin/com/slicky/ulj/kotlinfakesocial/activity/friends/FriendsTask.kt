package com.slicky.ulj.kotlinfakesocial.activity.friends

import com.slicky.ulj.kotlinfakesocial.activity.ProgressDialogTask
import com.slicky.ulj.kotlinfakesocial.db.FakeDBHandler
import com.slicky.ulj.kotlinfakesocial.model.person.Person
import java.io.IOException

/**
 * Created by SlickyPC on 24.5.2017
 */
internal class FriendsTask(private val activity: FriendsActivity
) : ProgressDialogTask<List<Person>>(activity, "Loading Friends...") {

    @Throws(IOException::class)
    override fun backgroundTask() = FakeDBHandler.getFriends()

    override fun success(result: List<Person>) = activity.friendsAdapter.setFriends(result)

    override fun fail(e: Exception) = activity.onFail("Could not retrieve Friends!", e)
}
