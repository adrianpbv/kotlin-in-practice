package f_201_starting_coroutines.s_12

import kotlinx.coroutines.*

suspend fun longTask() = coroutineScope {
    val uno = async { // // this coroutine will be awaited anyway, call its content directly
        delay(1000)
        println("Finished task 1")
        1
    }
    val two = async { // this coroutine will be awaited anyway after the first one
        delay(2000) //  this one only waits 1 second after 1st started
        println("Finished task 2")
        2
    }
    println("Finished task 3")
    println("${uno.await() + two.await()}")
}

/**
 * Since coroutine scope functions cannot complete until all their children are completed,
 * a suspend function must await all the coroutines started on it.
 */
suspend fun main() {
    println("Before")
    longTask()
    println("After")
}
// Before
// (1 sec)
// Finished task 1
// (1 sec)
// Finished task 2
// After

// “After” will be printed when all the coroutines started on longTask have completed because
// coroutineScope must await the completion of all its children.

// In practice:
// suspend fun updateUser() = coroutineScope {
//    // Don't
//    launch { sendEvent(UserSunchronized) }
//    // should be (to call synchronously)
//    // sendEvent(UserSunchronized) // call this directly
//    // or (to call asynchronously), if we have scope property
//    // scope.launch { sendEvent(UserSunchronized) }
//}