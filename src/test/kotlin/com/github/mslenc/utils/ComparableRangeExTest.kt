package com.github.mslenc.utils

import kotlin.test.*

class ComparableRangeExTest {
    @Test fun testComparableStringRangeEx() {
        val range1 = ComparableRangeEx("a", "z")
        val range2 = ComparableRangeEx("a", "z")
        val range3 = ComparableRangeEx("a", "a")
        val range4 = ComparableRangeEx("b", "b")

        assertEquals("a", range1.start)
        assertEquals("z", range1.endExclusive)
        assertFalse(range1.isEmpty())
        assertTrue("a" in range1)
        assertTrue("b" in range1)
        assertFalse("z" in range1)

        assertTrue(range3.isEmpty())
        assertTrue(range4.isEmpty())

        assertEquals(range1.hashCode(), range2.hashCode())
        assertEquals(range1, range2)
        assertNotEquals(range1, range3)
        assertEquals(range3.hashCode(), range4.hashCode())
        assertEquals(range3, range4)
    }
}