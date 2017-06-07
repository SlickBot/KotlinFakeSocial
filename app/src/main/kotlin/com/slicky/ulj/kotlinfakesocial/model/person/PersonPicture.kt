package com.slicky.ulj.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by SlickyPC on 17.5.2017
 */
data class PersonPicture(
        val large: String,
        val medium: String,
        val thumbnail: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<PersonPicture> = object : Parcelable.Creator<PersonPicture> {
            override fun createFromParcel(source: Parcel): PersonPicture = PersonPicture(source)
            override fun newArray(size: Int): Array<PersonPicture?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(large)
        dest?.writeString(medium)
        dest?.writeString(thumbnail)
    }
}
