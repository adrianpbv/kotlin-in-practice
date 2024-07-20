package f_210_testing.s_8

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*

/**
 * TestScope collects all exceptions with CoroutineExceptionHandler.
 * we can also use functions like advanceUntilIdle, advanceTimeBy,
 * or the currentTime property, all of each is thanks to the scheduler used
 * by this scope
 */
fun main() {
    val scope = TestScope()

    scope.launch {
        delay(1000)
        println("First done")
        delay(1000)
        println("Coroutine done")
    }

    println("[${scope.currentTime}] Before") // [0] Before
    scope.advanceTimeBy(1000)
    scope.runCurrent() // First done
    println("[${scope.currentTime}] Middle") // [1000] Middle
    scope.advanceUntilIdle() // Coroutine done
    println("[${scope.currentTime}] After") // [2000] After
}
