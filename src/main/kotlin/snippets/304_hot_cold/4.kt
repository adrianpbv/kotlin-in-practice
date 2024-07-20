package f_304_hot_cold.s_4

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * Channels, as hot data streams, produce elements independently of their consumption
 * and then keep them. They do not care how many receivers there are.
 * Since each element can be received only once, after the first receiver consumes all the elements,
 * the second one will find a channel that is empty and closed already.
 *
 */

private fun CoroutineScope.makeChannel() = produce {
    println("Channel started")
    for (i in 1..3) {
        delay(1000)
        send(i)
    }
}

suspend fun main() = coroutineScope {
    val channel = makeChannel()

    delay(1000)
    println("Calling channel...")
    for (value in channel) {
        println(value)
    }
    println("Consuming again...")
    for (value in channel) { // the channel is empty and closed already
        println(value)
    }
}
