package f_205_job.s_16

import kotlinx.coroutines.*

/**
 * CompletableDeferred is useful when some coroutines need to await some value or event,
 * that is produced by another coroutine.
 */

fun main(): Unit = runBlocking {
    val deferred = CompletableDeferred<String>()
    launch {
        println("Starting first")
        delay(1000)
        deferred.complete("Test")
        delay(1000)
        println("First done")
    }
    launch {
        println("Starting second")
        println(deferred.await()) // Wait for deferred to complete
        println("Second done")
    }
}
// Starting first/Starting second
// (1 sec)  // await for result, can be a value or an exception with completeExceptionally
// Test
// Second done
// (1 sec)
// First done