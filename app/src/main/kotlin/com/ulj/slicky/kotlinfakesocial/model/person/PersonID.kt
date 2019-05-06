package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by SlickyPC on 17.5.2017
 */
class PersonID : Parcelable {

    val name: String
    val value: String?

    constructor(name: String,
                value: String) {
        this.name = name
        this.value = value
    }

    private constructor(`in`: Parcel) {
        this.name = `in`.readString()!!
        this.value = `in`.readString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.name)
        dest.writeString(this.value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonID> {
        override fun createFromParcel(source: Parcel): PersonID {
            return PersonID(source)
        }
        override fun newArray(size: Int): Array<PersonID?> {
            return arrayOfNulls(size)
        }
    }

}
