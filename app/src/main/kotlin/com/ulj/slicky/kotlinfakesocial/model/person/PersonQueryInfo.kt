package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by SlickyPC on 17.5.2017
 */
@Parcelize
data class PersonQueryInfo(val seed: String,
                           val results: Int,
                           val page: Int,
                           val version: String) : Parcelable