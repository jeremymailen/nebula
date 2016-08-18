package org.jmailen.nebula.infrastructure.messaging.support

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.Matcher
import org.jetbrains.spek.api.Spek
import org.junit.Assert.assertThat
import kotlin.test.assertFalse
import kotlin.test.assertTrue

data class TestCase(val topicFilter: String, val topic: String, val shouldMatch: Boolean)

class TopicTreeTest : Spek({

    describe("TopicTreeTest") {

        arrayOf(TestCase("hello", "hello", true),
                TestCase("hello/there", "hello/there", true),
                TestCase("hello/there", "hello", false),
                TestCase("hello", "hello/there/pal", false),
                TestCase("#", "client", true),
                TestCase("client/#", "client", false),
                TestCase("client/#", "client/1", true),
                TestCase("client/#", "client/1/actionId/3", true),
                TestCase("+", "user4", true),
                TestCase("+", "user4/1", false),
                TestCase("user/+/session", "user/4/session", true),
                TestCase("user/+/session/+/event/+", "user/3/session/e5c/event/8", true),
                TestCase("user/+/session/+/event/+", "user/3/session/e5c/ping", false)).forEach { tc ->

            val subject = TopicTree()

            on("subscribe to [${tc.topicFilter}] and deliver message on [${tc.topic}]") {
                val message = "hi".toByteArray()
                var received: ByteArray? = null

                val isNewTopicFilter = subject.subscribe(tc.topicFilter) { received = it }
                subject.deliver(tc.topic, message)

                it("signals new topic filter") {
                    assertTrue(isNewTopicFilter)
                }

                it("received is ${tc.shouldMatch}") {
                    val deliveredMatcher: Matcher<in ByteArray?> =
                            if (tc.shouldMatch) equalTo(message) else nullValue()
                    assertThat(received, deliveredMatcher)
                }

                on("second subscriber") {
                    val isNewTopicFilter2nd = subject.subscribe(tc.topicFilter) {}

                    it("signals not new topic filter") {
                        assertFalse(isNewTopicFilter2nd)
                    }
                }
            }
        }
    }
})
