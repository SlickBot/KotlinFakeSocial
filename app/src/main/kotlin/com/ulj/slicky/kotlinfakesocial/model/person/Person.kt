package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by SlickyPC on 17.5.2017
 */
class Person(
        val gender: String,
        val name: PersonName,
        val location: PersonLocation,
        val email: String,
        val login: PersonLogin,
        val dob: Date,
        val registered: Date,
        val phone: String,
        val cell: String,
        val id: PersonID,
        val picture: PersonPicture,
        val nat: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Person> = object : Parcelable.Creator<Person> {
            override fun createFromParcel(source: Parcel): Person = Person(source)
            override fun newArray(size: Int): Array<Person?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readParcelable<PersonName>(PersonName::class.java.classLoader),
            source.readParcelable<PersonLocation>(PersonLocation::class.java.classLoader),
            source.readString(), source.readParcelable<PersonLogin>(PersonLogin::class.java.classLoader),
            Date(source.readLong()),
            Date(source.readLong()),
            source.readString(), source.readString(),
            source.readParcelable<PersonID>(PersonID::class.java.classLoader),
            source.readParcelable<PersonPicture>(PersonPicture::class.java.classLoader),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(gender)
        dest?.writeParcelable(name, 0)
        dest?.writeParcelable(location, 0)
        dest?.writeString(email)
        dest?.writeParcelable(login, 0)
        dest?.writeLong(dob.time)
        dest?.writeLong(registered.time)
        dest?.writeString(phone)
        dest?.writeString(cell)
        dest?.writeParcelable(id, 0)
        dest?.writeParcelable(picture, 0)
        dest?.writeString(nat)
    }
}
