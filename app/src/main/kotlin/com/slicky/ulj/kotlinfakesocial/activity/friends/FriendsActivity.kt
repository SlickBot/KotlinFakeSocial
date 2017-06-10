package com.slicky.ulj.kotlinfakesocial.activity.friends

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.slicky.ulj.kotlinfakesocial.R
import com.slicky.ulj.kotlinfakesocial.activity.BackableActivity
import com.slicky.ulj.kotlinfakesocial.activity.login.LoginActivity
import com.slicky.ulj.kotlinfakesocial.db.FakeDBHandler
import com.slicky.ulj.kotlinfakesocial.displayAlert
import com.slicky.ulj.kotlinfakesocial.findView
import com.slicky.ulj.kotlinfakesocial.model.person.Person
import com.slicky.ulj.kotlinfakesocial.startActivity

/**
 * Created by SlickyPC on 22.5.2017
 */
class FriendsActivity : BackableActivity() {

    companion object {
        private val TAG = FriendsActivity::class.java.canonicalName
    }

    private lateinit var friendsAdapter: FriendsAdapter

    private var friendsTask: FriendsTask? = null

    private val recycler by findView<RecyclerView>(R.id.friends_recycler_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friends_activity)

        friendsAdapter = FriendsAdapter(this, recycler)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = friendsAdapter
    }

    override fun onStart() {
        super.onStart()
        friendsTask = FriendsTask(this).apply { execute() }
    }

    override fun onStop() {
        super.onStop()
        friendsTask?.cancel()
    }

    internal fun setFriends(friends: List<Person>) {
        friendsAdapter.friends = friends
    }

    internal fun onFail(text: String, e: Exception?) {
        displayAlert(text + if (e != null) "\n" + e.localizedMessage else "") {
            setCancelable(false)
            setPositiveButton("Sign Out") { _, _ ->
                logOut()
            }
        }
        Log.wtf(TAG, text, e)
    }

    private fun logOut() {
        FakeDBHandler.signout()
        startActivity<LoginActivity>()
        finish()
    }
}
