package f_206_cancellation.s_10

import kotlinx.coroutines.*

/**
 * We can use the isActive property to check if a job is still active and
 * stop calculations if it is not.
 */

suspend fun main(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        do {
            Thread.sleep(200)
            println("Printing")
        } while (isActive)
    }
    delay(1100)
    job.cancelAndJoin()
    println("Cancelled successfully")
}
