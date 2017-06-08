package com.slicky.ulj.kotlinfakesocial.activity.detail

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.slicky.ulj.kotlinfakesocial.*
import com.slicky.ulj.kotlinfakesocial.activity.BackableActivity
import com.slicky.ulj.kotlinfakesocial.activity.profile.ProfileActivity
import com.slicky.ulj.kotlinfakesocial.activity.profile.ProfileActivity.Companion.startFriendProfile
import com.slicky.ulj.kotlinfakesocial.model.content.Content
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.util.*

/**
 * Created by SlickyPC on 8.6.2017
 */
class DetailActivity : BackableActivity() {

    companion object {
        private val TAG = ProfileActivity::class.java.canonicalName
        private val KEY_CONTENT = TAG + ".content"

        fun Activity.startDetail(content: Content) {
            startActivity<DetailActivity>(KEY_CONTENT to content)
        }
    }
    private val imageView by findView<ImageView>(R.id.detail_image)
    private val nameField by findView<TextView>(R.id.detail_owner_name)
    private val postedAtField by findView<TextView>(R.id.detail_posted_at)
    private val textField by findView<TextView>(R.id.detail_text)

    private lateinit var content: Content

    private var task: RemoveTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val extras = intent.extras
        content = extras.getParcelable(KEY_CONTENT)

        with(content) {
            val imageUrl = owner.picture.large
            val name = owner.fullName()
            val postedAtDate = Date(postedAt).formattedWithTime()

            Picasso.with(this@DetailActivity).load(imageUrl)
                    .placeholder(R.drawable.ic_user)
                    .transform(CropCircleTransformation())
                    .into(imageView)

            nameField.text = name
            postedAtField.text = "Posted at: " + postedAtDate
            textField.text = text
        }
    }

    override fun onStop() {
        super.onStop()
        if (task != null)
            task!!.cancel()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.detail_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_remove) {
            displayConfirmationDialog()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @Suppress("UNUSED_PARAMETER")
    fun onOpenProfile(view: View) {
        startFriendProfile(content.owner)
    }

    private fun startRemoveTask() {
        task = RemoveTask(this, content).apply { execute() }
    }

    internal fun handleError(text: String, e: Exception?) {
        displayDialog(text + if (e != null) "\n" + e.localizedMessage else "")
        Log.wtf(TAG, text, e)
    }

    private fun displayConfirmationDialog() {
        runOnUiThread {
            val builder = AlertDialog.Builder(this@DetailActivity, R.style.AppTheme_Dialog)
                    .setMessage("Do you really want to remove this Content?")
                    .setPositiveButton("Yes") { _, _ -> startRemoveTask() }
                    .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
            builder.create().show()
        }
    }

    private fun displayDialog(text: String) {
        runOnUiThread {
            val builder = AlertDialog.Builder(this@DetailActivity, R.style.AppTheme_Dialog)
                    .setMessage(text)
            builder.create().show()
        }
    }
}