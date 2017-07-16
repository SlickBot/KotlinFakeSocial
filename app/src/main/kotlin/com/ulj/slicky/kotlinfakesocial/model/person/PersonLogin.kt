package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by SlickyPC on 17.5.2017
 */
data class PersonLogin(
        val username: String,
        val password: String,
        val salt: String,
        val md5: String,
        val sha1: String,
        val sha256: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<PersonLogin> = object : Parcelable.Creator<PersonLogin> {
            override fun createFromParcel(source: Parcel): PersonLogin = PersonLogin(source)
            override fun newArray(size: Int): Array<PersonLogin?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(username)
        dest?.writeString(password)
        dest?.writeString(salt)
        dest?.writeString(md5)
        dest?.writeString(sha1)
        dest?.writeString(sha256)
    }
}