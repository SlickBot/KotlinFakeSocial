package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by SlickyPC on 14.4.2017
 */
@Parcelize
data class PersonQuery(
        val results: List<Person>,
        val info: PersonQueryInfo
) : Parcelable