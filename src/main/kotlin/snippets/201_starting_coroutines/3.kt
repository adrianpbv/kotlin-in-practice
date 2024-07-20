package f_201_starting_coroutines.s_3

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

suspend fun main() {
    // The async builder behaves just like launch,
    // but its lambda expression is expected to return a value
    val value1 = GlobalScope.async {
        delay(2000L)
        1
    }
    val value2 = GlobalScope.async {
        delay(2000L)
        2
    }
    val value3 = GlobalScope.async {
        delay(2000L)
        3
    }
    println("Calculating")
    print(value1.await())
    print(value2.await())
    print(value3.await())
}
// Calculating
// (2 sec)
// 123 (order is guaranteed, as we await for values in order)

// It is important to note that the launch and async builders start a coroutine
// immediately. Both join and await only await the completion of those coroutines.