package f_305_flow_introduction.s_5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun usersFlow(): Flow<String> = flow {
    repeat(3) {
        delay(1000)
        val ctx = currentCoroutineContext()
        val name = ctx[CoroutineName]?.name
        emit("User$it in $name")
    }
}

/**
 * Flow’s terminal operations (like collect) suspend a coroutine instead of blocking a thread.
 * They also support other coroutine functionalities, such as respecting the coroutine context
 * and handling exceptions
 */
suspend fun main() {
    val users = usersFlow()

    withContext(CoroutineName("Name")) {
        val job = launch {
            // collect is suspending
            users.collect { println(it) }
        }

        launch {
            delay(2100)
            println("I got enough")
            job.cancel() // launch cancellation also leads to proper flow processing cancellation.
        }
    }
}
