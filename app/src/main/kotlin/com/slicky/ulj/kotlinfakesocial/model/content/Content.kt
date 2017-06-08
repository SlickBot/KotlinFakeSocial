package com.slicky.ulj.kotlinfakesocial.model.content

import android.os.Parcel
import android.os.Parcelable
import com.slicky.ulj.kotlinfakesocial.model.person.Person

/**
 * Created by SlickyPC on 17.5.2017
 */
data class Content(val id: Long, val owner: Person, val text: String, val postedAt: Long) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Content> = object : Parcelable.Creator<Content> {
            override fun createFromParcel(source: Parcel): Content = Content(source)
            override fun newArray(size: Int): Array<Content?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readLong(),
            source.readParcelable<Person>(Person::class.java.classLoader),
            source.readString(),
            source.readLong()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeParcelable(owner, 0)
        dest.writeString(text)
        dest.writeLong(postedAt)
    }
}
