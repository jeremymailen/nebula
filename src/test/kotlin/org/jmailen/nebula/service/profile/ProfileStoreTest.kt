package org.jmailen.nebula.service.profile

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItems
import org.jetbrains.spek.api.Spek
import org.junit.Assert.assertThat

data class TestCase<out I, out E>(val input: I, val expected: E)

class ProfileStoreTest : Spek({

    describe("ProfileStore") {
        var subject: ProfileStore = ProfileStore()

        beforeEach {
            subject = ProfileStore()
        }

        on("clear") {
            it("removes all profiles") {
                subject.clear()
                assertThat(subject.list().size, equalTo(0))
            }
        }

        on("list") {
            it("lists all profiles") {
                assertThat(subject.list(), hasItems(jeremy, ray))
            }
        }

        on("get") {
            arrayOf(TestCase(1, jeremy),
                    TestCase(2, ray)).forEach { tc ->

                it("returns ${tc.expected.name} for ${tc.input}") {
                    assertThat(subject.get(tc.input), equalTo(tc.expected))
                }
            }
        }

        on("update") {
            val newJeremy = Profile(1, "Jeremy Malken", "+14153217654")
            val george = Profile(3, "George Georgio", "+19161234567")

            arrayOf(TestCase(george, george to ProfileStatus.CREATED),
                    TestCase(newJeremy, jeremy to ProfileStatus.REPLACED),
                    TestCase(ray, ray to ProfileStatus.REPLACED)).forEach { tc ->

                it("${tc.expected.second} profile ${tc.input.id}") {
                        assertThat(subject.update(tc.input), equalTo(tc.expected))
                }
            }
        }
    }
})
