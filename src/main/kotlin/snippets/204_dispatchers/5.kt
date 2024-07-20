package f_204_dispatchers.s_5

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * Dispatchers have a limited number of threads
 * private val pool = ...
 * Dispatchers.IO = pool limited to 64
 * Dispatchers.IO.limitedParallelism(x) = pool limited to x
 * Dispatchers.Default = pool limited to coresNum
 * Dispatchers.Default.limitedParallelism(x) =
 *     Dispatchers.Default limited to x
 */
suspend fun main(): Unit = coroutineScope {
    launch {
        printCoroutinesTime(Dispatchers.IO)
        // Dispatchers.IO took: 2074
    }
    
    launch {
        val dispatcher = Dispatchers.IO
            .limitedParallelism(100)
        printCoroutinesTime(dispatcher)
        // LimitedDispatcher@XXX took: 1082
    }
}

suspend fun printCoroutinesTime(
    dispatcher: CoroutineDispatcher
) {
    val test = measureTimeMillis {
        coroutineScope {
            repeat(100) {
                launch(dispatcher) {
                    Thread.sleep(1000)
                }
            }
        }
    }
    println("$dispatcher took: $test")
}

/**
 * Dispatchers.Default is limited to the number of cores.
 * Dispatchers.IO is limited to 64 (or the number of cores)
 */
