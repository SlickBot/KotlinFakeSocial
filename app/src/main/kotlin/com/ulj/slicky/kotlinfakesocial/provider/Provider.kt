package com.ulj.slicky.kotlinfakesocial.provider

import com.ulj.slicky.kotlinfakesocial.model.person.Person
import com.ulj.slicky.kotlinfakesocial.model.person.PersonDOB
import com.ulj.slicky.kotlinfakesocial.model.person.PersonID
import com.ulj.slicky.kotlinfakesocial.model.person.PersonLocation
import com.ulj.slicky.kotlinfakesocial.model.person.PersonLogin
import com.ulj.slicky.kotlinfakesocial.model.person.PersonName
import com.ulj.slicky.kotlinfakesocial.model.person.PersonPicture
import com.ulj.slicky.kotlinfakesocial.model.person.PersonRegistered

import java.util.ArrayList
import java.util.Date

object Provider {

    fun getContent(): String {
        return "this is test content"
    }

    fun getPersons(amount: Int): List<Person> {
        val persons = ArrayList<Person>()
        for (i in 0 until amount) {
            persons.add(getPerson(i))
        }
        return persons
    }

    private fun getPerson(id: Int): Person {
        return Person(
                "gender",
                PersonName(
                        "title",
                        "first",
                        "last"
                ),
                PersonLocation(
                        "street",
                        "city",
                        "state",
                        "postcode"
                ),
                "email",
                PersonLogin(
                        "username",
                        "password",
                        "salt",
                        "md5",
                        "sha1",
                        "sha256"
                ),
                PersonDOB(
                        Date(),
                        21
                ),
                PersonRegistered(
                        Date(),
                        21
                ),
                "phone",
                "cell",
                PersonID(
                        "name",
                        id.toString()
                ),
                PersonPicture(
                        "https://randomuser.me/api/portraits/men/65.jpg",
                        "https://randomuser.me/api/portraits/men/65.jpg",
                        "https://randomuser.me/api/portraits/men/65.jpg"
                ),
                "nat"
        )
    }

}
