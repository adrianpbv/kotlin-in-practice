package f_205_job.s_11

import kotlinx.coroutines.*

suspend fun main(): Unit = coroutineScope {
    val job = Job()
    launch(job) { // the new job replaces one from parent
        delay(1000)
        println("Text 1")
    }
    launch(job) { // the new job replaces one from parent
        delay(2000)
        println("Text 2")
    }
    job.children.forEach { it.join() } // join (wait) all the job’s current children.
}

/**
 * Effective Kotlin Item 32: Consider factory functions instead of constructors.
 */
