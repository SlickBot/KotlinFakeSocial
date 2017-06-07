package com.slicky.ulj.kotlinfakesocial.activity.content

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.slicky.ulj.kotlinfakesocial.R
import com.slicky.ulj.kotlinfakesocial.activity.ProgressDialogTask
import com.slicky.ulj.kotlinfakesocial.activity.about.AboutActivity
import com.slicky.ulj.kotlinfakesocial.activity.creator.CreatorActivity
import com.slicky.ulj.kotlinfakesocial.activity.friends.FriendsActivity
import com.slicky.ulj.kotlinfakesocial.activity.login.LoginActivity
import com.slicky.ulj.kotlinfakesocial.activity.profile.ProfileActivity
import com.slicky.ulj.kotlinfakesocial.activity.settings.SettingsActivity
import com.slicky.ulj.kotlinfakesocial.db.DummyDBHandler
import com.slicky.ulj.kotlinfakesocial.findView
import com.slicky.ulj.kotlinfakesocial.model.content.Content
import com.slicky.ulj.kotlinfakesocial.model.person.Person

class ContentActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        private val TAG = ContentActivity::class.java.canonicalName
    }

    private val navigationView by findView<NavigationView>(R.id.nav_view)
    private val drawer by findView<DrawerLayout>(R.id.drawer_layout)
    private val toolbar by findView<Toolbar>(R.id.toolbar)
    private val recycler by findView<RecyclerView>(R.id.content_recycler_view)

    private lateinit var contentAdapter: ContentAdapter

    private var contentTask: ContentTask? = null
    private var userTask: ProgressDialogTask<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        if (!DummyDBHandler.isSignedIn)
            logOut()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_activity)
        setSupportActionBar(toolbar)

        // Thanks Lollipop -> https://stackoverflow.com/a/29455956/6814029
        title = "Content"

        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        contentAdapter = ContentAdapter(this, recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = contentAdapter
    }

    override fun onStart() {
        super.onStart()
        contentTask = ContentTask(this).apply { execute() }
    }

    override fun onStop() {
        super.onStop()
        contentTask?.cancel()
        userTask?.cancel()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> startUserTask()
            R.id.nav_friends -> openFriends()
            R.id.nav_settings -> openSettings()
            R.id.nav_about -> openAbout()
            R.id.nav_share -> share()
            R.id.nav_logout -> logOut()
        }
        drawer.closeDrawers()
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.content_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_create) {
            openCreator()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startUserTask() {
        userTask = UserTask(this).apply { execute() }
    }

    private fun openCreator() {
        val intent = Intent(this, CreatorActivity::class.java)
        startActivity(intent)
    }

    internal fun openOwnerProfile(person: Person) {
        val intent = ProfileActivity.getOwnerIntent(this, person)
        startActivity(intent)
    }

    internal fun openFriendProfile(person: Person) {
        val intent = ProfileActivity.getFriendIntent(this, person)
        startActivity(intent)
    }

    private fun openFriends() {
        val intent = Intent(this, FriendsActivity::class.java)
        startActivity(intent)
    }

    private fun openSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun openAbout() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    private fun share() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Fakest Social Network!")
            putExtra(Intent.EXTRA_TEXT, "This app is really FAKE!")
        }
        startActivity(Intent.createChooser(intent, "Share via"))
    }

    fun setContent(contents: List<Content>) {
        contentAdapter.setContent(contents)
    }

    private fun logOut() {
        DummyDBHandler.signout()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    internal fun handleError(text: String, e: Exception?) {
        displaySignOutDialog(text + if (e != null) "\n" + e.localizedMessage else "")
        Log.wtf(TAG, text, e)
    }

    private fun displaySignOutDialog(text: String) {
        runOnUiThread {
            val builder = AlertDialog.Builder(this@ContentActivity, R.style.AppTheme_Dialog)
                    .setMessage(text)
                    .setCancelable(false)
                    .setPositiveButton("Sign Out", { _, _ -> logOut() })
            builder.create().show()
        }
    }
}
