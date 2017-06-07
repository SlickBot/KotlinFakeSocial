package com.slicky.ulj.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by SlickyPC on 17.5.2017
 */
data class PersonLocation(
        val street: String,
        val city: String,
        val state: String,
        val postcode: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<PersonLocation> = object : Parcelable.Creator<PersonLocation> {
            override fun createFromParcel(source: Parcel): PersonLocation = PersonLocation(source)
            override fun newArray(size: Int): Array<PersonLocation?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(street)
        dest?.writeString(city)
        dest?.writeString(state)
        dest?.writeString(postcode)
    }
}
