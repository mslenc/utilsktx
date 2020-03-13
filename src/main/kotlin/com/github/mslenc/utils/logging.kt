package com.github.mslenc.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

@Suppress("NOTHING_TO_INLINE")
inline fun (() -> Any?).toStringSafe(): String {
    return try {
        invoke().toString()
    } catch (e: Exception) {
        "Log message invocation failed: $e"
    }
}

inline fun Logger.trace(msg: () -> Any?) {
    if (isTraceEnabled) trace(msg.toStringSafe())
}

inline fun Logger.debug(msg: () -> Any?) {
    if (isDebugEnabled) debug(msg.toStringSafe())
}

inline fun Logger.info(msg: () -> Any?) {
    if (isInfoEnabled) info(msg.toStringSafe())
}

inline fun Logger.warn(msg: () -> Any?) {
    if (isWarnEnabled) warn(msg.toStringSafe())
}

inline fun Logger.error(msg: () -> Any?) {
    if (isErrorEnabled) error(msg.toStringSafe())
}

inline fun Logger.trace(t: Throwable?, msg: () -> Any?) {
    if (isTraceEnabled) trace(msg.toStringSafe(), t)
}

inline fun Logger.debug(t: Throwable?, msg: () -> Any?) {
    if (isDebugEnabled) debug(msg.toStringSafe(), t)
}

inline fun Logger.info(t: Throwable?, msg: () -> Any?) {
    if (isInfoEnabled) info(msg.toStringSafe(), t)
}

inline fun Logger.warn(t: Throwable?, msg: () -> Any?) {
    if (isWarnEnabled) warn(msg.toStringSafe(), t)
}

inline fun Logger.error(t: Throwable?, msg: () -> Any?) {
    if (isErrorEnabled) error(msg.toStringSafe(), t)
}

inline fun <reified T> getLogger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

fun KClass<*>.getLogger(): Logger {
    return LoggerFactory.getLogger(java)
}