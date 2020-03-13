package com.github.mslenc.utils

import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.assertEquals

class CachedAsyncMapTest {
    @Test
    fun testBasicsMap() = runBlocking {
        val counter = AtomicInteger(0)

        val cache = CachedAsyncMap<Int, String> {
            counter.incrementAndGet()
            "[$it]"
        }

        assertEquals("[1]", cache.get(1))
        assertEquals("[2]", cache.get(2))
        assertEquals("[3]", cache.get(3))
        assertEquals("[2]", cache.get(2))
        assertEquals("[3]", cache.get(3))
        assertEquals("[4]", cache.get(4))
        assertEquals("[3]", cache.get(3))
        assertEquals("[2]", cache.get(2))

        assertEquals(4, counter.get())
    }
}