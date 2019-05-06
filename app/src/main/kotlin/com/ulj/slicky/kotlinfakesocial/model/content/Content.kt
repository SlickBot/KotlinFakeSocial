package com.ulj.slicky.kotlinfakesocial.model.content

import android.os.Parcel
import android.os.Parcelable

import com.ulj.slicky.kotlinfakesocial.model.person.Person

/**
 * Created by SlickyPC on 17.5.2017
 */
class Content : Parcelable {

    val id: Long
    val owner: Person
    val text: String
    val postedAt: Long

    constructor(id: Long,
                owner: Person,
                text: String,
                postedAt: Long) {
        this.id = id
        this.owner = owner
        this.postedAt = postedAt
        this.text = text
    }

    private constructor(`in`: Parcel) {
        this.id = `in`.readLong()
        this.owner = `in`.readParcelable(Person::class.java.classLoader)!!
        this.text = `in`.readString()!!
        this.postedAt = `in`.readLong()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(this.id)
        dest.writeParcelable(this.owner, flags)
        dest.writeString(this.text)
        dest.writeLong(this.postedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun hashCode(): Int {
        return id.toInt()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other == null)
            return false
        if (other !is Content)
            return false
        val content = other as Content?
        return id == content!!.id
    }

    companion object CREATOR : Parcelable.Creator<Content> {
        override fun createFromParcel(parcel: Parcel): Content {
            return Content(parcel)
        }
        override fun newArray(size: Int): Array<Content?> {
            return arrayOfNulls(size)
        }
    }

}
