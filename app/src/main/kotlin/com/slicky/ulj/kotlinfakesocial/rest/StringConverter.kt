package com.slicky.ulj.kotlinfakesocial.rest

import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Created by SlickyPC on 17.5.2017
 */
class StringConverter : Converter.Factory() {

    override fun responseBodyConverter(type: Type?,
                                       annotations: Array<Annotation>?,
                                       retrofit: Retrofit?
    ): Converter<ResponseBody, *>? {
        if (String::class.java == type)
            return Converter<ResponseBody, String> { value -> value.string() }
        return null
    }

    override fun requestBodyConverter(type: Type?,
                                      parameterAnnotations: Array<Annotation>?,
                                      methodAnnotations: Array<Annotation>?,
                                      retrofit: Retrofit?
    ): Converter<*, RequestBody>? {
        if (String::class.java == type)
            return Converter<String, RequestBody> { value -> RequestBody.create(MediaType.parse("text/plain"), value) }
        return null
    }
}
