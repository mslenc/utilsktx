package com.github.mslenc.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

inline fun safeToString(producer: () -> Any?): String {
    return try {
        producer().toString()
    } catch (e: Exception) {
        "Log message invocation failed: $e"
    }
}

inline fun Logger.trace(msg: () -> Any?) {
    if (isTraceEnabled) trace(safeToString(msg))
}

inline fun Logger.debug(msg: () -> Any?) {
    if (isDebugEnabled) debug(safeToString(msg))
}

inline fun Logger.info(msg: () -> Any?) {
    if (isInfoEnabled) info(safeToString(msg))
}

inline fun Logger.warn(msg: () -> Any?) {
    if (isWarnEnabled) warn(safeToString(msg))
}

inline fun Logger.error(msg: () -> Any?) {
    if (isErrorEnabled) error(safeToString(msg))
}

inline fun Logger.trace(t: Throwable?, msg: () -> Any?) {
    if (isTraceEnabled) trace(safeToString(msg), t)
}

inline fun Logger.debug(t: Throwable?, msg: () -> Any?) {
    if (isDebugEnabled) debug(safeToString(msg), t)
}

inline fun Logger.info(t: Throwable?, msg: () -> Any?) {
    if (isInfoEnabled) info(safeToString(msg), t)
}

inline fun Logger.warn(t: Throwable?, msg: () -> Any?) {
    if (isWarnEnabled) warn(safeToString(msg), t)
}

inline fun Logger.error(t: Throwable?, msg: () -> Any?) {
    if (isErrorEnabled) error(safeToString(msg), t)
}

inline fun <reified T> getLogger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

fun KClass<*>.getLogger(): Logger {
    return LoggerFactory.getLogger(java)
}