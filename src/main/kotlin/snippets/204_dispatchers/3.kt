package f_204_dispatchers.s_3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

// dispatchers have a limited number of threads they can use at the same time.
// Default is limited by the number of cores in your processor.
// The limit of Dispatchers.IO is 64 (or the number of cores if there are more).
suspend fun main() = coroutineScope {
    repeat(1000) {
        launch(Dispatchers.IO) {
            Thread.sleep(200)

            val threadName = Thread.currentThread().name
            println("Running on thread: $threadName")
        }
    }
}
