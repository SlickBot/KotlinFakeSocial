package com.slicky.ulj.kotlinfakesocial.activity.friends

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.slicky.ulj.kotlinfakesocial.R
import com.slicky.ulj.kotlinfakesocial.activity.BackableActivity
import com.slicky.ulj.kotlinfakesocial.activity.login.LoginActivity
import com.slicky.ulj.kotlinfakesocial.activity.profile.ProfileActivity
import com.slicky.ulj.kotlinfakesocial.db.DummyDBHandler
import com.slicky.ulj.kotlinfakesocial.findView
import com.slicky.ulj.kotlinfakesocial.model.person.Person

/**
 * Created by SlickyPC on 22.5.2017
 */
class FriendsActivity : BackableActivity() {
    companion object {
        private val TAG = FriendsActivity::class.java.canonicalName
    }

    private val recycler by findView<RecyclerView>(R.id.friends_recycler_view)
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

    internal fun openFriendProfile(friend: Person) {
        val intent = ProfileActivity.getFriendIntent(this, friend)
        startActivity(intent)
    }

    internal fun setFriends(friends: List<Person>) {
        friendsAdapter.setFriends(friends)
    }

    internal fun onFail(text: String, e: Exception?) {
        displaySignOutDialog(text + if (e != null) "\n" + e.localizedMessage else "")
        Log.wtf(TAG, text, e)
    }

    private fun logOut() {
        DummyDBHandler.signout()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun displaySignOutDialog(text: String) {
        runOnUiThread {
            val builder = AlertDialog.Builder(this@FriendsActivity, R.style.AppTheme_Dialog)
                    .setMessage(text)
                    .setCancelable(false)
                    .setPositiveButton("Sign Out", { _, _ -> logOut() })
            builder.create().show()
        }
    }
}
