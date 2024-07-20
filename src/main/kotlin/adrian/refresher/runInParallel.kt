package adrian.refresher.m1

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

suspend fun main() {
    //runSynchronousOperations()
    runSyncOpInParallel()
    // exception handling
    // test function execution time
}

suspend fun runSynchronousOperations() {
    println("Start 1st demo work")
    val time1 = measureTimeMillis {
        coroutineScope {
            val jobGetVendors = getVendors() // it will be awaited
            jobGetVendors.forEach { vendor -> // get parts Synchronously
                println("got vendor: $vendor")
                getVendorParts(vendor).forEach {
                    println("part: $it")
                    updateVendorParts(vendor, it)
                }
            }
        }
    }
    println("1st demo work took $time1\n\n")
}

suspend fun runSyncOpInParallel() {
    println("Start 2nd demo work")
    val time2 = measureTimeMillis {
        coroutineScope {
            val jobGetVendors = getVendors()
            jobGetVendors.forEach { vendor ->
                launch { // asynchronous coroutine builder
                    println("got vendor: $vendor")
                    getVendorParts(vendor).forEach {
                        println("part: $it")
                        updateVendorParts(vendor, it)
                    }
                }
            }
            println("i don't wait the update to finish")
        }
    }
    println("2nd demo work took $time2")
}

suspend fun getVendors(): List<String> {
    delay(2000L)
    return List(100) { "vendor $it" }
}

suspend fun getVendorParts(vendor: String): List<String> {
    delay(2000L)
    return List(1000){ "$vendor p$" }
}

suspend fun updateVendorParts(vendor: String, part: String) {
    delay(2000)
    println("Updated $vendor -> $part")
}