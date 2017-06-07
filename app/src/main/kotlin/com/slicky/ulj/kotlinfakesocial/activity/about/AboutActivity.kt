package com.slicky.ulj.kotlinfakesocial.activity.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.slicky.ulj.kotlinfakesocial.R
import com.slicky.ulj.kotlinfakesocial.activity.BackableActivity
import com.slicky.ulj.kotlinfakesocial.rest.ApiServices

/**
 * Created by SlickyPC on 22.5.2017
 */
class AboutActivity : BackableActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_activity)
    }

    @Suppress("UNUSED_PARAMETER")
    fun onRandomUserClick(view: View) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(ApiServices.PERSON_URL))
        startActivity(browserIntent)
    }

    @Suppress("UNUSED_PARAMETER")
    fun onWatchOutClick(view: View) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(ApiServices.CONTENT_URL))
        startActivity(browserIntent)
    }
}
