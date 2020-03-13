package com.github.mslenc.utils

import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.assertEquals
import kotlin.test.assertNull

class CachedAsyncNullableTest {
    @Test fun testBasics() = runBlocking {
        val counter = AtomicInteger(0)

        val cacheAbcd = CachedAsyncNullable<String> { counter.incrementAndGet(); "abcd" }
        val cacheNull = CachedAsyncNullable<String> { counter.incrementAndGet(); null }

        assertEquals(0, counter.get())
        assertEquals("abcd", cacheAbcd.get())
        assertEquals(1, counter.get())
        assertEquals("abcd", cacheAbcd.get())
        assertEquals(1, counter.get())
        assertNull(cacheNull.get())
        assertEquals(2, counter.get())
        assertNull(cacheNull.get())
        assertEquals(2, counter.get())
    }
}