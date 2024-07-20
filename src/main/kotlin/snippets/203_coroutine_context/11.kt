package f_203_coroutine_context.s_11

import kotlinx.coroutines.*

/**
 * If coroutineScope is called in a suspending function, the coroutine it creates is a direct child
 * of the coroutine that started this function, therefore it inherits the context from the parent
 * coroutine.
 */

suspend fun printName() = coroutineScope {
    println(coroutineContext[CoroutineName]?.name)
}

fun main() = runBlocking(CoroutineName("Outer")) {
    printName() // Outer
    launch(CoroutineName("Inner")) {
        printName() // Inner
    }
    delay(10)
    printName() // Outer
}
