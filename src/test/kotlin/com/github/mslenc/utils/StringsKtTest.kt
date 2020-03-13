package com.github.mslenc.utils

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

internal class StringsKtTest {
    @Test fun testTrimToNull() {
        assertNull(null.trimToNull())
        assertNull("".trimToNull())
        assertNull("    \t\r\n   ".trimToNull())

        assertEquals("abc", "   abc   ".trimToNull())
        assertEquals("the  one", "  the  one  ".trimToNull())
        assertEquals("the  one", "the  one  ".trimToNull())
        assertEquals("the  one", "  the  one".trimToNull())
    }
}