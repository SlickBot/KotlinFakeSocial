package com.slicky.ulj.kotlinfakesocial.db

import com.slicky.ulj.kotlinfakesocial.model.content.Content
import com.slicky.ulj.kotlinfakesocial.model.person.Person
import java.io.IOException

/**
 * Created by SlickyPC on 20.5.2017
 */
interface DBHandler {

    @Throws(IOException::class)
    fun signin(email: String, password: String): Boolean

    @Throws(IOException::class)
    fun signup(firstName: String,
               lastName: String,
               email: String,
               password: String): Boolean

    fun signout()

    @Throws(IOException::class)
    fun uploadContent(content: String): Boolean

    @Throws(IOException::class)
    fun getUser(): Person

    @Throws(IOException::class)
    fun getFriends(): List<Person>

    @Throws(IOException::class)
    fun getContent(): List<Content>
}
