package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by SlickyPC on 17.5.2017
 */
class PersonPicture : Parcelable {

    val large: String
    val medium: String
    val thumbnail: String

    constructor(large: String,
                medium: String,
                thumbnail: String) {
        this.large = large
        this.medium = medium
        this.thumbnail = thumbnail
    }

    private constructor(`in`: Parcel) {
        this.large = `in`.readString()!!
        this.medium = `in`.readString()!!
        this.thumbnail = `in`.readString()!!
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.large)
        dest.writeString(this.medium)
        dest.writeString(this.thumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonPicture> {
        override fun createFromParcel(source: Parcel): PersonPicture {
            return PersonPicture(source)
        }
        override fun newArray(size: Int): Array<PersonPicture?> {
            return arrayOfNulls(size)
        }
    }

}
