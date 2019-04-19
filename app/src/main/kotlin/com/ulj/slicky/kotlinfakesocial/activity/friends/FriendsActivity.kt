package com.ulj.slicky.kotlinfakesocial.activity.friends

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.ulj.slicky.kotlinfakesocial.R
import com.ulj.slicky.kotlinfakesocial.activity.BackableActivity
import com.ulj.slicky.kotlinfakesocial.activity.login.LoginActivity
import com.ulj.slicky.kotlinfakesocial.db.FakeDBHandler
import com.ulj.slicky.kotlinfakesocial.displayAlert
import com.ulj.slicky.kotlinfakesocial.model.person.Person
import com.ulj.slicky.kotlinfakesocial.startActivity
import kotlinx.android.synthetic.main.friends_activity.friends_recycler_view as recycler

/**
 * Created by SlickyPC on 22.5.2017
 */
class FriendsActivity : BackableActivity() {

    companion object {
        private val TAG = FriendsActivity::class.java.canonicalName
    }

    private lateinit var friendsAdapter: FriendsAdapter

    private var friendsTask: FriendsTask? = null

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
