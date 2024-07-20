package f_103_suspension.s_6

import java.util.concurrent.*
import kotlinx.coroutines.*
import kotlin.coroutines.resume

private val executor =
    Executors.newSingleThreadScheduledExecutor {
        Thread(it, "scheduler").apply { isDaemon = true }
    }

/**
 * Delay function of older Kotlin versions
 * it uses the same thread for the coroutines using the delay function
 */
suspend fun delay(timeMillis: Long): Unit =
    suspendCancellableCoroutine { cont ->
        executor.schedule({
            cont.resume(Unit)
        }, timeMillis, TimeUnit.MILLISECONDS)
    }

suspend fun main() {
    println("Before")

    delay(1000)

    println("After")
}
