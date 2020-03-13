package com.github.mslenc.utils

fun String?.trimToNull(): String? {
    if (this == null)
        return null

    val trimmed = this.trim()
    if (trimmed.isEmpty())
        return null

    return trimmed
}