package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by SlickyPC on 17.5.2017
 */
class PersonLogin : Parcelable {

    val username: String
    val password: String
    val salt: String
    val md5: String
    val sha1: String
    val sha256: String

    constructor(username: String,
                password: String,
                salt: String,
                md5: String,
                sha1: String,
                sha256: String) {
        this.username = username
        this.password = password
        this.salt = salt
        this.md5 = md5
        this.sha1 = sha1
        this.sha256 = sha256
    }

    private constructor(`in`: Parcel) {
        this.username = `in`.readString()!!
        this.password = `in`.readString()!!
        this.salt = `in`.readString()!!
        this.md5 = `in`.readString()!!
        this.sha1 = `in`.readString()!!
        this.sha256 = `in`.readString()!!
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.username)
        dest.writeString(this.password)
        dest.writeString(this.salt)
        dest.writeString(this.md5)
        dest.writeString(this.sha1)
        dest.writeString(this.sha256)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonLogin> {
        override fun createFromParcel(source: Parcel): PersonLogin {
            return PersonLogin(source)
        }
        override fun newArray(size: Int): Array<PersonLogin?> {
            return arrayOfNulls(size)
        }
    }
}
