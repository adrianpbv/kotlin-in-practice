package adrian.refresher.m2

import kotlinx.coroutines.*
import adrian.refresher.RefresherRepository
import kotlin.system.measureTimeMillis

@OptIn(ExperimentalCoroutinesApi::class)
fun limitedIODispatcher(threadLimit: Int) = Dispatchers.IO.limitedParallelism(threadLimit)

suspend fun main() {
    val dispatcher = limitedIODispatcher(100)
    val refresherRepository = RefresherRepository(dispatcher)
    val vendors = refresherRepository.getVendors()
//    val time1 = measureTimeMillis {
//        coroutineScope {
//            vendors.forEach { vendor -> // get parts Synchronously
//                println("got vendor: $vendor")
//                refresherRepository.getVendorParts(vendor).forEach {
//                    println("part: $it")
//                    refresherRepository.updateVendorParts(vendor, it)
//                }
//            }
//        }
//    }
//    println("1st demo work took $time1\n\n")
    println("Start synchronous operations in parallel")

    val time2 = measureTimeMillis {
        coroutineScope {
            vendors.forEach { vendor ->
                launch { // asynchronous coroutine builder
                    println("got vendor: $vendor")
                    refresherRepository.getVendorParts(vendor).forEach { part ->
                        launch {
                            println("$vendor got part: $part")
                            val res = refresherRepository.getInventory(vendor, part)
                            yield()
                            refresherRepository.updateVendorParts(vendor, part, res)
                        }
                    }
                    yield() // to allow cancellation and redispatching between blocking functions
                }
            }
            println("i don't wait the update to finish")
        }
    }
    println("2nd demo work took $time2\n\n")
    // exception handling
    // test function execution time
}

suspend fun testDispatcherPerformance() {
    val time = measureTimeMillis {
        coroutineScope {
            repeat(100) {
                launch(limitedIODispatcher(100)) {
                    blocking()
                }
            }
        }
    }
    println("Completed in $time ms")
}

fun blocking() {
    Thread.sleep(1000)
}
suspend fun suspending() {
    delay(1000)
}