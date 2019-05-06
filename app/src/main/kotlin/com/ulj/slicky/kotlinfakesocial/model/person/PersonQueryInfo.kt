package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by SlickyPC on 17.5.2017
 */
class PersonQueryInfo : Parcelable {

    val seed: String
    val results: Int
    val page: Int
    val version: String

    constructor(seed: String,
                results: Int,
                page: Int,
                version: String) {
        this.seed = seed
        this.results = results
        this.page = page
        this.version = version
    }

    private constructor(`in`: Parcel) {
        this.seed = `in`.readString()!!
        this.results = `in`.readInt()
        this.page = `in`.readInt()
        this.version = `in`.readString()!!
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.seed)
        dest.writeInt(this.results)
        dest.writeInt(this.page)
        dest.writeString(this.version)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonQueryInfo> {
        override fun createFromParcel(source: Parcel): PersonQueryInfo {
            return PersonQueryInfo(source)
        }
        override fun newArray(size: Int): Array<PersonQueryInfo?> {
            return arrayOfNulls(size)
        }
    }

}
