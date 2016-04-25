package org.jmailen.nebula.service.profile

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItems
import org.jetbrains.spek.api.Spek
import org.junit.Assert.assertThat
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ProfileStoreSpec : Spek() { init {

    given("Profile Store") {

        on("list") {
            var subject = ProfileStore()
            val results = subject.list()

            it("has the right number of profiles") {
                assertThat(results.count(), equalTo(2))
            }

            it("contains correct profile data") {
                assertThat(results, hasItems(jeremy, ray))
            }
        }

        on("get") {
            var subject = ProfileStore()

            it("returns a profile by id") {
                assertEquals(subject.get(2), ray)
            }

            it("returns nothing when profile does not exist") {
                assertNull(subject.get(999))
            }
        }

        on("update") {
            var subject = ProfileStore()
            var george = Profile(name = "George Georgio", phone = "+19161234567")
            val createResult = subject.update(george)

            it("returns the profile as created") {
                assertThat(createResult, equalTo(george to ProfileStatus.CREATED))
            }

            val modJeremy = jeremy.copy()
            modJeremy.phone = "+14153217654"
            val replaceResult = subject.update(modJeremy)

            it("returns the original profile as replaced") {
                assertThat(replaceResult, equalTo(jeremy to ProfileStatus.REPLACED))
            }
        }

        on("clear") {
            var subject = ProfileStore()

            it("removes all profiles") {
                subject.clear()
                assertThat(subject.profileMap.count(), equalTo(0))
            }
        }
    }
}}
