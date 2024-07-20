package f_201_starting_coroutines.s_9

import kotlinx.coroutines.delay
import kotlinx.coroutines.coroutineScope

/**
 * Coroutine scope functions are suspending functions that start a synchronous coroutine.
 * They start a new coroutine but suspend the current one until the new one is completed.
 * In practice, they behave a lot like runBlocking, but instead of blocking the thread
 * they suspend the coroutine.
 */

suspend fun main() {
    coroutineScope {
        delay(1000L)
        println("World!")
    }
    coroutineScope {
        delay(1000L)
        println("World!")
    }
    coroutineScope {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}
