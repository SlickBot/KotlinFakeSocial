package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by SlickyPC on 17.5.2017
 */
data class PersonID(
        val name: String,
        val value: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<PersonID> = object : Parcelable.Creator<PersonID> {
            override fun createFromParcel(source: Parcel): PersonID = PersonID(source)
            override fun newArray(size: Int): Array<PersonID?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString().orEmpty(),
            source.readString().orEmpty()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeString(value)
    }
}
