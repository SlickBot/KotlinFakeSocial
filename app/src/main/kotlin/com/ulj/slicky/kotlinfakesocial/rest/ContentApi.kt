package com.ulj.slicky.kotlinfakesocial.rest

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by SlickyPC on 17.5.2017
 */
interface ContentApi {

    @FormUrlEncoded
    @POST("/Random/RandomParagraph")
    fun getContent(
            @Field("Subject1") subject1: String = "",
            @Field("Subject2") subject2: String = ""
    ): Call<String>

}
