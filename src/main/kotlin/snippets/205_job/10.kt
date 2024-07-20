package f_205_job.s_10

import kotlinx.coroutines.*

suspend fun main(): Unit = coroutineScope {
    val job = Job() // job factory that can be the parent for some coroutines
    launch(job) { // the new job replaces one from parent
        delay(1000)
        println("Text 1")
    }
    launch(job) { // the new job replaces one from parent
        delay(2000)
        println("Text 2")
    }
    job.join() // Here we will await forever, this context is still ready (Active state)
    // to be used by other coroutines
    println("Will not be printed")
}
