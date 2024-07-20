package f_206_cancellation.s_9

import kotlinx.coroutines.*

/**
 * It is good practice to use yield in suspend functions between blocks of non-suspended
 * CPU-intensive or time-intensive operations.
 * Also use yield between two blocking operations, to allow cancellation and redispatching between them.
 *
 *  yield should be used in suspending functions that make multiple CPU-intensive
 *  or blocking operations.
 */

suspend fun main(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        repeat(1_000) { i ->
            Thread.sleep(200)
            yield()
            println("Printing $i")
        }
    }
    delay(1100)
    job.cancelAndJoin()
    println("Cancelled successfully")
    delay(1000)
}

/**
 * The yield function is a regular top-level suspension function that does not need any scope,
 * so it can be used in regular suspending functions. Since it does suspension and resuming,
 * it causes redispatching, which means that if there is a queue to the dispatcher,
 * this coroutine will return the thread and wait in the queue. This is considered positive
 * when our operations are demanding threads as it prevents other coroutines being starved
 */
