package com.ulj.slicky.kotlinfakesocial

import com.ulj.slicky.kotlinfakesocial.rest.ApiServices
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * Created by SlickyPC on 17.5.2017
 */
class ContentServiceTest {
    private var content: String? = null

    @Before
    fun can_receive_query() {
        content = ApiServices.contentApi
                .getContent("car", "apple" )
                .execute()
                .body()
    }

    @Test
    fun query_contains_results() {
        assertNotNull("Content result should not be null.", content)
        assertNotEquals("Content result should not be empty.", 0, content!!.length.toLong())
    }
}
