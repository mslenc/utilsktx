package com.github.mslenc.utils

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope

/**
 * Like regular map(), except it maps the things "concurrently", to improve batching of db calls.
 */
suspend inline fun <T, R> Iterable<T>.smap(crossinline transform: suspend (T) -> R): List<R> = supervisorScope {
    map { async(start = CoroutineStart.UNDISPATCHED) { transform(it) } }.awaitAll()
}

/**
 * Like regular mapTo(), except it maps the things "concurrently", to improve batching of db calls.
 */
suspend inline fun <T, R, C : MutableCollection<in R>> Iterable<T>.smapTo(destination: C, crossinline transform: suspend (T) -> R): C = supervisorScope {
    map { async(start = CoroutineStart.UNDISPATCHED) { transform(it) } }.forEach { destination += it.await() }
    destination
}

/**
 * Like regular forEach(), except it performs the operations "concurrently", to improve batching of db calls.
 */
suspend inline fun <T> Iterable<T>.sforEach(crossinline action: suspend (T) -> Unit): Unit = supervisorScope {
    map { async(start = CoroutineStart.UNDISPATCHED) { action(it) } }.awaitAll()
    Unit
}

