package f_204_dispatchers.s_9

import kotlinx.coroutines.*
import kotlin.coroutines.*

suspend fun main(): Unit =
    withContext(newSingleThreadContext("Thread1")) {
        var continuation: Continuation<Unit>? = null
        
        launch(newSingleThreadContext("Thread2")) {
            delay(1000)
            continuation?.resume(Unit)
        }
        
        launch(Dispatchers.Unconfined) {
            println(Thread.currentThread().name) // Thread1

            suspendCancellableCoroutine<Unit> {
                continuation = it
            }
            
            println(Thread.currentThread().name) // Thread2

            delay(1000)
            
            println(Thread.currentThread().name)
            // kotlinx.coroutines.DefaultExecutor
            // (used by delay)
        }
    }
/**
 * What if, by accident, we miss a blocking call and we are running on the Main thread?
 * This could lead to blocking the entire application. This is why we avoid using
 * Dispatchers.Unconfined in production code, except for some special cases.
 */
