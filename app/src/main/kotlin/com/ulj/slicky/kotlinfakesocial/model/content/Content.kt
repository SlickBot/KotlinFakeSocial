package com.ulj.slicky.kotlinfakesocial.model.content

import android.os.Parcelable
import com.ulj.slicky.kotlinfakesocial.model.person.Person
import kotlinx.android.parcel.Parcelize

/**
 * Created by SlickyPC on 17.5.2017
 */
@Parcelize
data class Content(
        val id: Long,
        val owner: Person,
        val text: String,
        val postedAt: Long
) : Parcelable