package com.github.mslenc.utils

import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.assertEquals

class CachedAsyncTest {
    @Test fun testBasics() = runBlocking {
        val counter = AtomicInteger(0)

        val cache = CachedAsync { counter.incrementAndGet() }

        assertEquals(1, cache.get())
        assertEquals(1, cache.get())
        assertEquals(1, cache.get())
    }
}