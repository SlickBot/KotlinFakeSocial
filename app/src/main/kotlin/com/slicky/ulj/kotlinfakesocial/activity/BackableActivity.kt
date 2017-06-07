package com.slicky.ulj.kotlinfakesocial.activity

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

/**
 * Created by SlickyPC on 22.5.2017
 */
open class BackableActivity : AppCompatActivity() {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            // NavUtils.navigateUpFromSameTask(this);
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
