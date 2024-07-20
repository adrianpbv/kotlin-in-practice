package f_207_exception_handling.s_4

import kotlinx.coroutines.*

/**
 * runBlocking uses a regular Job internally, The SupervisorJob is replaced by this internal Job
 * Then runBlocking throws this exception.
 */

fun main(): Unit = runBlocking(SupervisorJob()) {
    launch { // 1
        delay(1000)
        throw Error("Some error")
    }
    launch { // 2
        delay(2000)
        println("Will not be printed")
    }
}
