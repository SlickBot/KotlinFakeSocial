package com.slicky.ulj.kotlinfakesocial.activity.about

import android.os.Bundle
import android.view.View
import com.slicky.ulj.kotlinfakesocial.R
import com.slicky.ulj.kotlinfakesocial.activity.BackableActivity
import com.slicky.ulj.kotlinfakesocial.rest.ApiServices
import com.slicky.ulj.kotlinfakesocial.startBrowseActivity

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
        startBrowseActivity(ApiServices.PERSON_URL)
    }

    @Suppress("UNUSED_PARAMETER")
    fun onWatchOutClick(view: View) {
        startBrowseActivity(ApiServices.PERSON_URL)
    }
}
