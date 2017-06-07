package com.slicky.ulj.kotlinfakesocial.db

import com.slicky.ulj.kotlinfakesocial.model.content.Content
import com.slicky.ulj.kotlinfakesocial.model.person.Person
import com.slicky.ulj.kotlinfakesocial.rest.ApiServices
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by SlickyPC on 20.5.2017
 */
object DummyDBHandler : DBHandler {
    private val lock = Any()

    private var person: Person? = null
    private var friendList: List<Person>? = null
    private var contentList: List<Content>? = null

    var isSignedIn: Boolean = false
        private set

    @Throws(IOException::class)
    override fun signin(email: String, password: String): Boolean {
        // Simulate network work.
        simulateWork()
        // TODO: This should be changed.
        isSignedIn = email == "change@me.pls" && password == "password"
        return isSignedIn
    }

    override fun signout() {
        // Not simulating work because this should run on async thread.
        /* simulateWork(); */
        // Remove all data assigned to user.
        isSignedIn = false
        person = null
        friendList = null
        contentList = null
    }

    @Throws(IOException::class)
    override fun signup(firstName: String,
                        lastName: String,
                        email: String,
                        password: String): Boolean {
        // Simulate network work.
        simulateWork()
        // TODO: This should be changed.
        isSignedIn = true
        return isSignedIn
    }

    @Throws(IOException::class)
    override fun uploadContent(content: String): Boolean {
        person?.let {
            // Simulate network work.
            simulateWork()
            // Create new Content.
            val newContent = Content(it, content, System.currentTimeMillis())
            // Create new Content list with new Content in top.
            val newContents = ArrayList<Content>()
            newContents.add(newContent)
            newContents.addAll(contentList ?: emptyList())
            // Replace old Contents with new Contents.
            contentList = newContents
            return true
        }
        return false
    }

    override fun getUser(): Person {
        if (person == null)
            queryData()
        return person!!
    }

    override fun getFriends(): List<Person> {
        if (friendList == null)
            queryData()
        return friendList ?: emptyList()
    }

    override fun getContent(): List<Content> {
        if (contentList == null)
            queryData()
        return contentList ?: emptyList()
    }

    @Throws(IOException::class)
    private fun queryData() {
        synchronized(lock) {
            // Find person candidates.
            val candidates = findCandidates()
            // Use first person as user.
            person = candidates[0]
            // Use rest as friends.
            friendList = candidates.subList(1, candidates.size)
            // Generate content.
            contentList = generateContent()
        }
    }

    @Throws(IOException::class)
    private fun findCandidates(): List<Person> {
        // Query PersonQuery for new random persons.
        val query = ApiServices.personApi
                .getPerson(50)
                .execute().body() ?: throw IOException("Did not receive PersonQuery (is null)")

        // Distinct received persons by image URL.
        val candidates = distinctByURL(query.results)

        if (candidates.isEmpty())
            throw IOException("Did not receive enough candidates (size < 1)")

        return candidates
    }

    @Throws(IOException::class)
    private fun generateContent(): List<Content> {
        val list = ArrayList<Content>()
        val random = Random()

        friendList?.let {
            for (i in 0..9) {
                // Query ContentService for new Content text.
                val query = ApiServices.contentApi
                        .getContent()
                        .execute().body() ?: throw IOException("Did not receive Content (is null)")

                // Pick random Content owner.
                val randy = it[Random().nextInt(it.size)]

                // Find last post time or set it to currentTimeMillis.
                val lastPostTime = if (list.size > 0)
                    list[list.size - 1].postedAt
                else
                    System.currentTimeMillis()

                // Assign random delay between posts.
                val timePassed = random.nextInt(1000 * 60 * 60 * 24)

                // Create new Content and add it to list.
                val content = Content(randy, query, lastPostTime - timePassed)
                list.add(content)
            }
        }
        return list
    }

    private fun distinctByURL(people: List<Person>): List<Person> {
        val filtered = ArrayList<Person>()
        val urls = HashSet<String>()

        for (friend in people) {
            val url = friend.picture.large
            if (urls.add(url))
                filtered.add(friend)
        }
        return filtered
    }

    private fun simulateWork() {
        TimeUnit.SECONDS.sleep(1)
    }
}
