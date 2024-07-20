package f_210_testing.s_4

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher

/**
 * StandardTestDispatcher does not advance time by itself.
 * We need to do this, otherwise our coroutine will never be resumed.
 */

fun main() {
    val testDispatcher = StandardTestDispatcher()

    runBlocking(testDispatcher) {
        delay(1) // this will never run
        println("Coroutine done")
    }
}
