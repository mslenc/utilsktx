package com.github.mslenc.utils

import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class ScollectionsKtTest {
    @Test fun testSmap() = runBlocking {
        val res = listOf(1, 2, 3).smap { "[$it]" }

        assertEquals(listOf("[1]", "[2]", "[3]"), res)
    }

    @Test fun testSmapTo() = runBlocking {
        val res = listOf(1, 2, 3, 4, 5, 6).smapTo(LinkedHashSet()) { "[$it]" }

        val it = res.iterator()
        assertEquals("[1]", it.next())
        assertEquals("[2]", it.next())
        assertEquals("[3]", it.next())
        assertEquals("[4]", it.next())
        assertEquals("[5]", it.next())
        assertEquals("[6]", it.next())
        assertFalse(it.hasNext())
    }

    @Test fun testSforeach() = runBlocking {
        val counter = AtomicInteger(0)

        (1..12).sforEach { counter.addAndGet(it) }

        assertEquals(78, counter.get())
    }
}