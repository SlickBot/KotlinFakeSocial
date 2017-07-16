package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by SlickyPC on 17.5.2017
 */
data class PersonQueryInfo(
        val seed: String,
        val results: Int,
        val page: Int,
        val version: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<PersonQueryInfo> = object : Parcelable.Creator<PersonQueryInfo> {
            override fun createFromParcel(source: Parcel): PersonQueryInfo = PersonQueryInfo(source)
            override fun newArray(size: Int): Array<PersonQueryInfo?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(seed)
        dest?.writeInt(results)
        dest?.writeInt(page)
        dest?.writeString(version)
    }
}
