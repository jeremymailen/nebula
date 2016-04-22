package org.jmailen.nebula.service.profile

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItems
import org.jetbrains.spek.api.Spek
import org.junit.Assert.assertThat

class ProfileStoreSpec : Spek() { init {

    given("Initialized Store") {
        val subject = ProfileStore()

        on("list") {
            val results = subject.list()

            it("has the right number of profiles") {
                assertThat(results.count(), equalTo(2))
            }

            it("contains correct profile data") {
                assertThat(results, hasItems(
                        Profile("Jeremy Malken", "214123456"),
                        Profile("Ray Norquist", "415123456")))
            }
        }

    }
}}
