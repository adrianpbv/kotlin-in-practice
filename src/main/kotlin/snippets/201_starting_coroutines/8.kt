package f_201_starting_coroutines.s_8

import kotlinx.coroutines.*

/**
 * GlobalScope is an empty scope that configures nothing and builds no relationship with
 * coroutines started on it, therefore it is considered bad practice to use it because
 * it can easily break our relationships, and it cannot be used to control coroutines
 * started on it.
 */

fun main() = runBlocking {
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
}
