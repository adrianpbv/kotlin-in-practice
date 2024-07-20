package f_204_dispatchers.s_2

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

// Dispatchers.IO allows more than 50 active threads at the same time.
// It is designed to be used when we block threads with I/O operations,
// such as when we read/write files or call blocking functions.
suspend fun main() {
    val time = measureTimeMillis {
        coroutineScope {
            repeat(50) {
                launch(Dispatchers.IO) {
                    Thread.sleep(1000)
                }
            }
        }
    }
    println(time) // ~1000
}
