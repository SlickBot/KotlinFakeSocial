package com.ulj.slicky.kotlinfakesocial.rest

import com.ulj.slicky.kotlinfakesocial.model.person.PersonQuery
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by SlickyPC on 17.5.2017
 */
interface PersonApi {

    // results=[int]
    // gender=[male|female]
    // page=[int]
    // seed=[string]
    // nat=[AU|BR|CA|CH|DE|DK|ES|FI|FR|GB|IE|IR|NL|NZ|TR|US|...]
    @GET("/api")
    fun getPerson(
            @Query("results") results: Int,
            @Query("gender") gender: String? = null,
            @Query("seed") seed: String? = null,
            @Query("nat") nationality: String? = null,
            @Query("page") page: String? = null
    ): Call<PersonQuery>

}
