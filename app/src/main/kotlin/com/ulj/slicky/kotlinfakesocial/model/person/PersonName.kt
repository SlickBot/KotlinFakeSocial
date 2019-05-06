package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by SlickyPC on 17.5.2017
 */
class PersonName : Parcelable {

    val title: String
    val first: String
    val last: String

    constructor(title: String,
                first: String,
                last: String) {
        this.title = title
        this.first = first
        this.last = last
    }

    private constructor(`in`: Parcel) {
        this.title = `in`.readString()!!
        this.first = `in`.readString()!!
        this.last = `in`.readString()!!
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.title)
        dest.writeString(this.first)
        dest.writeString(this.last)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonName> {
        override fun createFromParcel(source: Parcel): PersonName {
            return PersonName(source)
        }
        override fun newArray(size: Int): Array<PersonName?> {
            return arrayOfNulls(size)
        }
    }

}
