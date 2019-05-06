package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by SlickyPC on 17.5.2017
 */
class PersonQuery : Parcelable {

    val results: List<Person>
    val info: PersonQueryInfo

    constructor(results: List<Person>,
                info: PersonQueryInfo) {
        this.results = results
        this.info = info
    }

    private constructor(`in`: Parcel) {
        this.results = `in`.createTypedArrayList(Person.CREATOR)!!
        this.info = `in`.readParcelable(PersonQueryInfo::class.java.classLoader)!!
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(this.results)
        dest.writeParcelable(this.info, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonQuery> {
        override fun createFromParcel(source: Parcel): PersonQuery {
            return PersonQuery(source)
        }
        override fun newArray(size: Int): Array<PersonQuery?> {
            return arrayOfNulls(size)
        }
    }

}
