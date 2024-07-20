package f_204_dispatchers.s_4

import kotlinx.coroutines.*

/**
 * Both Dispatchers.Default and Dispatchers.IO share the same pool of threads.
 * Threads are reused, and redispatching is often not needed.
 */
suspend fun main(): Unit = coroutineScope {
    launch(Dispatchers.Default) {
        println(Thread.currentThread().name)
        withContext(Dispatchers.IO) {
            println(Thread.currentThread().name)
        }
    }
}
