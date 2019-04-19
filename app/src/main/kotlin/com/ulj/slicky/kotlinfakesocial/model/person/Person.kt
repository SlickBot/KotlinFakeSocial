package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by SlickyPC on 17.5.2017
 */
@Parcelize
data class Person(
        val gender: String,
        val name: PersonName,
        val location: PersonLocation,
        val email: String,
        val login: PersonLogin,
        val dob: PersonDOB,
        val registered: PersonRegistered,
        val phone: String,
        val cell: String,
        val id: PersonID,
        val picture: PersonPicture,
        val nat: String
) : Parcelable