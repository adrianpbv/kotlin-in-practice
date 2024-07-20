package f_210_testing.s_9

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*

/**
 * UnconfinedTestDispatcher immediately invokes all the operations before the first delay on started coroutines,
 * which is why the code below prints “C”.
 */
fun main() {
    CoroutineScope(StandardTestDispatcher()).launch {
        print("A")
        delay(1)
        print("B")
    }
    CoroutineScope(UnconfinedTestDispatcher()).launch {
        print("C")
        delay(1)
        print("D")
    }
}
//  I recommend using StandardTestDispatcher instead of UnconfinedTestDispatcher,
//  as it is considered the new standard.
