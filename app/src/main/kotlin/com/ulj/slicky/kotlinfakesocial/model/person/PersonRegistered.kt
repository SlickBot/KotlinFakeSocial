package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable

import java.util.Date

/**
 * Created by root on 01/08/2018.
 */
class PersonRegistered : Parcelable {

    val date: Date
    val age: Int

    constructor(date: Date,
                age: Int) {
        this.date = date
        this.age = age
    }

    private constructor(`in`: Parcel) {
        val tmpDob = `in`.readLong()
        this.date = Date(tmpDob)
        this.age = `in`.readInt()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(this.date.time)
        dest.writeInt(this.age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonRegistered> {
        override fun createFromParcel(source: Parcel): PersonRegistered {
            return PersonRegistered(source)
        }
        override fun newArray(size: Int): Array<PersonRegistered?> {
            return arrayOfNulls(size)
        }
    }

}
