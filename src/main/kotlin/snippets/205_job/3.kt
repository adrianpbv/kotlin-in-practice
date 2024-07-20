package f_205_job.s_3

import kotlinx.coroutines.*

/**
 * Job is the only coroutine context that is not inherited by a coroutine from another coroutine.
 * Every coroutine creates its own Job
 */

fun main(): Unit = runBlocking {
    val deferred: Deferred<String> = async {
        delay(1000)
        "Test"
    }
    val job: Job = deferred
}
