package f_204_dispatchers.s_1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * The default dispatcher that is used is Dispatchers.Default which has a pool of threads
 * whose size is equal to the number of cores in the machine your code is running on
 * (but not less than two)
 */

// limit the number of threads used at the same time
@OptIn(ExperimentalCoroutinesApi::class)
private val dispatcher = Dispatchers.Default .limitedParallelism(5)

suspend fun main() = coroutineScope {
    repeat(1000) {
        launch { // or launch(Dispatchers.Default) {
            // To make it busy
            List(1_000_000) { Random.nextLong() }.maxOrNull()

            val threadName = Thread.currentThread().name
            println("Running on thread: $threadName")
        }
    }
}
