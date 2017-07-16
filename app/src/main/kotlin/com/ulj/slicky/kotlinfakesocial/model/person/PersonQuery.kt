package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by SlickyPC on 14.4.2017
 */
data class PersonQuery(
        val results: List<Person>,
        val info: PersonQueryInfo
) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<PersonQuery> = object : Parcelable.Creator<PersonQuery> {
            override fun createFromParcel(source: Parcel): PersonQuery = PersonQuery(source)
            override fun newArray(size: Int): Array<PersonQuery?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            ArrayList<Person>().apply {
                source.readList(this, Person::class.java.classLoader)
            }, source.readParcelable<PersonQueryInfo>(PersonQueryInfo::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeList(results)
        dest?.writeParcelable(info, 0)
    }
}