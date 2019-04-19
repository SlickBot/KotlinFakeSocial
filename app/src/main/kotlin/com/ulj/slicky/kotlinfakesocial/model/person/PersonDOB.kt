package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by root on 01/08/2018.
 */
@Parcelize
data class PersonDOB(
        val date: Date,
        val age: Int
) : Parcelable