package com.slicky.ulj.kotlinfakesocial.activity.content

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
import com.slicky.ulj.kotlinfakesocial.activity.settings.SettingsActivity
import com.slicky.ulj.kotlinfakesocial.db.FakeDBHandler
import com.slicky.ulj.kotlinfakesocial.findView
import com.slicky.ulj.kotlinfakesocial.startActivity
import com.slicky.ulj.kotlinfakesocial.startShareActivity

class ContentActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        private val TAG = ContentActivity::class.java.canonicalName
    }

    private val navigationView by findView<NavigationView>(R.id.nav_view)
    private val drawer by findView<DrawerLayout>(R.id.drawer_layout)
    private val toolbar by findView<Toolbar>(R.id.toolbar)
    private val recycler by findView<RecyclerView>(R.id.content_recycler_view)

    internal lateinit var contentAdapter: ContentAdapter

    private var contentTask: ContentTask? = null
    private var userTask: ProgressDialogTask<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        if (!FakeDBHandler.isSignedIn)
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
            R.id.nav_profile -> userTask = UserTask(this).apply { execute() }
            R.id.nav_friends -> startActivity<FriendsActivity>()
            R.id.nav_settings -> startActivity<SettingsActivity>()
            R.id.nav_about -> startActivity<AboutActivity>()
            R.id.nav_share -> startShareActivity("Fakest Social Network!", "This app is really FAKE!")
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
            startActivity<CreatorActivity>()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logOut() {
        startActivity<LoginActivity>()
        FakeDBHandler.signout()
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
