package com.ulj.slicky.kotlinfakesocial

import com.ulj.slicky.kotlinfakesocial.model.person.PersonQuery
import com.ulj.slicky.kotlinfakesocial.rest.ApiServices
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * Created by SlickyPC on 17.5.2017
 */
class PersonServiceTest {
    private var query: PersonQuery? = null

    @Before
    fun can_receive_query() {
        query = ApiServices.personApi.getPerson(
                results = 4,
                seed = "1"
        ).execute().body()
    }

    @Test
    fun query_is_not_null() {
        assertNotNull("After querying person query should not be null.", query)
    }

    @Test
    fun query_contains_results() {
        assertNotNull("Query results should not be null.", query!!.results)
        assertEquals("Query results should contain 5 items.", 5, query!!.results.size.toLong())
    }
}
