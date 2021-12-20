package com.github.mslenc.utils

class ComparableRangeEx<T>(val start: T, val endExclusive: T)
        where T: Comparable<T> {

    operator fun contains(value: T) = value >= start && value < endExclusive

    fun isEmpty() = start >= endExclusive

    override fun equals(other: Any?) = when {
        other !is ComparableRangeEx<*> -> false
        isEmpty() -> other.isEmpty()
        else -> start == other.start && endExclusive == other.endExclusive
    }

    override fun hashCode() = when {
        isEmpty() -> -1
        else -> 31 * start.hashCode() + endExclusive.hashCode()
    }

    override fun toString(): String = "$start..<$endExclusive"
}