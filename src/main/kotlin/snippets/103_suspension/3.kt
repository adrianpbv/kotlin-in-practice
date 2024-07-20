package f_103_suspension.s_3

import kotlinx.coroutines.*
import kotlin.coroutines.resume

suspend fun main() {
    println("Before")

    suspendCancellableCoroutine<Unit> { continuation ->
        // continuation This is the object that we can use to resume the coroutine
        println("Before too")
        continuation.resume(Unit)// resume immediately
    }

    println("After")
}
