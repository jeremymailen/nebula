package org.jmailen.nebula.service.profile

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import org.hamcrest.CoreMatchers.`is` as eqTo

class ProfileControllerTest {

    val testProfile = Profile(name = "Bob", phone = "999")
    @Mock lateinit var store: ProfileStore

    lateinit var subject: ProfileController

    @Before fun setup() {
        initMocks(this)
        subject = ProfileController(store)
    }

    @Test fun get() {
        whenever(store.get(1)).thenReturn(testProfile)
        assertThat(subject.get(1), eqTo(testProfile))
        verify(store, times(1)).get(1)
    }
}
