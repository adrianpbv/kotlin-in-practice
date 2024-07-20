package f_201_starting_coroutines.s_1

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Asynchronous coroutines builders: launch and async
fun main() {
    // launch doesn't wait for the coroutine to finish,
    // so it is immediately followed by the next line of code.
    GlobalScope.launch {
        delay(1000L)
        println("World!")
    }
    GlobalScope.launch {
        delay(1000L)
        println("World!")
    }
    GlobalScope.launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    Thread.sleep(2000L) // we need to block the thread to wait for the coroutines to finish.
}
