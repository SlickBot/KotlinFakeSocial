package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable

import java.util.Date

/**
 * Created by root on 01/08/2018.
 */
class PersonDOB : Parcelable {

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

    companion object CREATOR : Parcelable.Creator<PersonDOB> {
        override fun createFromParcel(source: Parcel): PersonDOB {
            return PersonDOB(source)
        }
        override fun newArray(size: Int): Array<PersonDOB?> {
            return arrayOfNulls(size)
        }
    }

}