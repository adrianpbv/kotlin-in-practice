package f_201_starting_coroutines.s_5

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    // runBlocking starts a coroutine and blocks the current thread until this coroutine
    // is completed. It is effectively synchronous because its execution takes
    // as long as the coroutine needs to complete.
    runBlocking {
        delay(1000L)
        println("World!")
    }
    runBlocking {
        delay(1000L)
        println("World!")
    }
    runBlocking {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}
// (1 sec)
// World!
// (1 sec)
// World!
// (1 sec)
// World!
// Hello,