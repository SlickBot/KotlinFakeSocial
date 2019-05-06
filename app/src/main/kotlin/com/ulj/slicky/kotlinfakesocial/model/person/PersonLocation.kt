package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by SlickyPC on 17.5.2017
 */
class PersonLocation : Parcelable {

    val street: String
    val city: String
    val state: String
    val postcode: String

    constructor(street: String,
                city: String,
                state: String,
                postcode: String) {
        this.street = street
        this.city = city
        this.state = state
        this.postcode = postcode
    }

    private constructor(`in`: Parcel) {
        this.street = `in`.readString()!!
        this.city = `in`.readString()!!
        this.state = `in`.readString()!!
        this.postcode = `in`.readString()!!
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.street)
        dest.writeString(this.city)
        dest.writeString(this.state)
        dest.writeString(this.postcode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonLocation> {
        override fun createFromParcel(source: Parcel): PersonLocation {
            return PersonLocation(source)
        }
        override fun newArray(size: Int): Array<PersonLocation?> {
            return arrayOfNulls(size)
        }
    }
}
