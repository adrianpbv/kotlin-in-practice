package f_206_cancellation.s_11

import kotlinx.coroutines.*

/**
 * Alternatively, we might use the ensureActive() function, which throws
 * CancellationException if Job is not active.
 *
 * The ensureActive() function needs to be called on a CoroutineScope (or CoroutineContext, or Job).
 * All it does is throw an exception if the job is no longer active.
 * ensureActive() is lighter than yield()
 */

suspend fun main(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        repeat(1000) { num ->
            Thread.sleep(200)
            ensureActive()
            println("Printing $num")
        }
    }
    delay(1100)
    job.cancelAndJoin()
    println("Cancelled successfully")
}
