package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by SlickyPC on 17.5.2017
 */
@Parcelize
data class PersonLogin(val username: String,
                       val password: String,
                       val salt: String,
                       val md5: String,
                       val sha1: String,
                       val sha256: String) : Parcelable