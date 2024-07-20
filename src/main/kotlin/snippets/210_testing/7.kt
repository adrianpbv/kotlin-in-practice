package f_210_testing.s_7

import kotlinx.coroutines.*
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlin.random.Random
import kotlin.system.measureTimeMillis

/**
 * Adding Thread.sleep will not influence the coroutine with StandardTestDispatcher.
 * The call to advanceUntilIdle takes only a few milliseconds,
 * so it does not wait for any real time. It immediately pushes the virtual time
 * and executes coroutine operations.
 */
fun main() {
    val dispatcher = StandardTestDispatcher()

    CoroutineScope(dispatcher).launch {
        delay(1000)
        println("Coroutine done")
    }

    Thread.sleep(Random.nextLong(2000)) // Does not matter
    // how much time we wait here, it will not influence
    // the result

    val time = measureTimeMillis {
       println("[${dispatcher.scheduler.currentTime}] Before")
       dispatcher.scheduler.advanceUntilIdle()
       println("[${dispatcher.scheduler.currentTime}] After")
    }
    println("Took $time ms")
}
