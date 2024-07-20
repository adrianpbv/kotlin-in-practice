package f_207_exception_handling.s_3

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    // DON'T DO THAT!
    launch(SupervisorJob()) { // 1
        launch {
            delay(1000)
            throw Error("Some error")
        }

        launch {
            delay(2000)
            println("Will not be printed")
        }
    }
    launch { // this has his own scope
        delay(2000)
        println("Will be printed2")
    }
    delay(3000)
}
