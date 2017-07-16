package com.ulj.slicky.kotlinfakesocial.rest

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

/**
 * Created by SlickyPC on 6.6.2017
 */
object ApiServices {

    val PERSON_URL = "https://randomuser.me/"
    val CONTENT_URL = "http://watchout4snakes.com/wo4snakes/"

    private val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()

    private val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()

    val personApi: PersonApi by lazy {
        Retrofit.Builder()
                .baseUrl(PERSON_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
                .create<PersonApi>(PersonApi::class.java)
    }

    val contentApi: ContentApi by lazy {
        Retrofit.Builder()
                .baseUrl(CONTENT_URL)
                .addConverterFactory(StringConverter())
                .client(okHttpClient)
                .build()
                .create<ContentApi>(ContentApi::class.java)
    }
}
