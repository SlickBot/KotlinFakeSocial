package com.slicky.ulj.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by SlickyPC on 17.5.2017
 */
data class PersonName(
        val title: String,
        val first: String,
        val last: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<PersonName> = object : Parcelable.Creator<PersonName> {
            override fun createFromParcel(source: Parcel): PersonName = PersonName(source)
            override fun newArray(size: Int): Array<PersonName?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(title)
        dest?.writeString(first)
        dest?.writeString(last)
    }
}
