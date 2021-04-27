package com.github.mslenc.utils

import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.collections.LinkedHashSet
import kotlin.math.sqrt
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

    @Test fun testSfilter() = runBlocking {
        val squares = (1..50).sfilter {
            val sq = sqrt(it.toDouble()).toInt()
            sq * sq == it
        }

        assertEquals(listOf(1, 4, 9, 16, 25, 36, 49), squares)
    }

    @Test fun testSmapNotNull() = runBlocking {
        val squares = (1..50).smapNotNull {
            val sq = sqrt(it.toDouble()).toInt()
            when (sq * sq) {
                it -> it.toString()
                else -> null
            }
        }

        assertEquals(listOf("1", "4", "9", "16", "25", "36", "49"), squares)
    }

    @Test fun testSmapNotNullTo() = runBlocking {
        val squares = (1..50).smapNotNullTo(TreeSet<String>()) {
            val sq = sqrt(it.toDouble()).toInt()
            when (sq * sq) {
                it -> it.toString()
                else -> null
            }
        }

        assertEquals(listOf("1", "16", "25", "36", "4", "49", "9"), squares.toList())
    }
}