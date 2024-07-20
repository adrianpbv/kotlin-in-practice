package f_206_cancellation.s_3

import kotlinx.coroutines.*

/**
 * invokeOnCompletion:
 * a handler to be called when the job reaches a terminal state,
 * namely either “Completed” or “Cancelled”.
 */

suspend fun main(): Unit = coroutineScope {
    val job = launch {
        repeat(1_000) { i ->
            delay(200)
            println("Printing $i")
        }
    }
    job.invokeOnCompletion { // invokeOnCompletion is guaranteed to be called exactly once (assuming this coroutine ever completes)
        if (it is CancellationException) {
            println("Cancelled with $it")
        }
        println("Finally")
    }
    delay(700)
    job.cancelAndJoin()
    println("Cancelled successfully")
    delay(1000)
}
