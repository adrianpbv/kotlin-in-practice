package f_206_cancellation.s_6

import kotlinx.coroutines.*

/**
 * suspending calls or starting coroutines operations are not allowed in the “Cancelling” state
 * Calling suspending functions in this state will throw CancellationException,
 * and starting a new coroutine will be ignored.
 *
 * use withContext(NonCancellable) for all suspending calls that should be executed even in
 * the “Cancelling” state.
 *
 * NonCancellable is a job that is always active,
 * and it should not be used outside this particular situation,
 */

suspend fun main(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        try {
            println("Coroutine started")
            delay(200)
            println("Coroutine finished")
        } finally {
            println("Finally")
            launch {
                // cleaning up resources
                println("Children executed")
            }
            delay(1000L)
            println("Cleanup done")
        }
    }
    delay(100)
    job.cancelAndJoin()
    println("Done")
}

//  suspend fun operation()
//  { try {
//          // operation
//  } finally {
//          withContext(NonCancellable) {
//              // cleanup that requires suspending call
//          }
//    }
//  }
