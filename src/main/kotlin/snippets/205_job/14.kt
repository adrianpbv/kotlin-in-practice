package f_205_job.s_14

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
    job.complete() // The complete function can be used after we start the last coroutine on a job.
    job.join()// we can just use the join function to wait for the job to complete.
}
