package com.ulj.slicky.kotlinfakesocial.model.person

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by SlickyPC on 17.5.2017
 */
class Person : Parcelable {

    val gender: String
    val name: PersonName
    val location: PersonLocation
    val email: String
    val login: PersonLogin
    val dob: PersonDOB
    val registered: PersonRegistered
    val phone: String
    val cell: String
    val id: PersonID
    val picture: PersonPicture
    val nat: String

    constructor(gender: String,
                name: PersonName,
                location: PersonLocation,
                email: String,
                login: PersonLogin,
                dob: PersonDOB,
                registered: PersonRegistered,
                phone: String,
                cell: String,
                id: PersonID,
                picture: PersonPicture,
                nat: String) {
        this.gender = gender
        this.name = name
        this.location = location
        this.email = email
        this.login = login
        this.dob = dob
        this.registered = registered
        this.phone = phone
        this.cell = cell
        this.id = id
        this.picture = picture
        this.nat = nat
    }

    private constructor(`in`: Parcel) {
        this.gender = `in`.readString()!!
        this.name = `in`.readParcelable(PersonName::class.java.classLoader)!!
        this.location = `in`.readParcelable(PersonLocation::class.java.classLoader)!!
        this.email = `in`.readString()!!
        this.login = `in`.readParcelable(PersonLogin::class.java.classLoader)!!
        this.dob = `in`.readParcelable(PersonDOB::class.java.classLoader)!!
        this.registered = `in`.readParcelable(PersonRegistered::class.java.classLoader)!!
        this.phone = `in`.readString()!!
        this.cell = `in`.readString()!!
        this.id = `in`.readParcelable(PersonID::class.java.classLoader)!!
        this.picture = `in`.readParcelable(PersonPicture::class.java.classLoader)!!
        this.nat = `in`.readString()!!
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.gender)
        dest.writeParcelable(this.name, flags)
        dest.writeParcelable(this.location, flags)
        dest.writeString(this.email)
        dest.writeParcelable(this.login, flags)
        dest.writeParcelable(this.dob, flags)
        dest.writeParcelable(this.registered, flags)
        dest.writeString(this.phone)
        dest.writeString(this.cell)
        dest.writeParcelable(this.id, flags)
        dest.writeParcelable(this.picture, flags)
        dest.writeString(this.nat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(source: Parcel): Person {
            return Person(source)
        }
        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }

}
