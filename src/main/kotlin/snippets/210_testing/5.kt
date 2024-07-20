package f_210_testing.s_5

import kotlinx.coroutines.delay
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher

/**
 * advanceTimeBy with a concrete number of milliseconds. This function advances time and
 * executes all operations that happened in the meantime.
 */
fun main() {
    val testDispatcher = StandardTestDispatcher()

    CoroutineScope(testDispatcher).launch {
        delay(1)
        println("Done1")
    }
    CoroutineScope(testDispatcher).launch {
        delay(2)
        println("Done2")
    }
    testDispatcher.scheduler.advanceTimeBy(2) // Done
    testDispatcher.scheduler.runCurrent() // Done2 // resume after second 2
}
// resume operations scheduled exactly at the second millisecond,
// we need to additionally invoke the runCurrent function.