package f_207_exception_handling.s_2

import kotlinx.coroutines.*

/**
 * SupervisorJob is a special kind of job that ignores all exceptions in its children.
 * Thanks to this, an exception in one coroutine will not cancel this scope and all its children.
 */

fun main(): Unit = runBlocking {
    val scope = CoroutineScope(SupervisorJob())
    scope.launch {
        launch {
            delay(1000)
            println("will not be printed1")
            launch {
                delay(1000)
                println("will not be printed2")
            }
        }
        launch {
            delay(500)
            throw Error("Some error")
        }
    }
    scope.launch {
        delay(2000)
        println("Will be printed")
    }
    delay(3000)
    println(scope.isActive) // the scope remains active.
}
