package adrian.refresher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class RefresherRepository(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : IRefresherRepository {
    var counter = 0
    private val mutex = Mutex()

    override suspend fun getVendors(): List<String> {
        delay(1000) // suspend the coroutine
        return List(1) { "vendor $it" }
    }

    override suspend fun getVendorParts(vendor: String): List<String> =
        withContext(dispatcher) {
            //Thread.sleep(1000L) // can be a blocking operation
            delay(1000L)
            List(5) { "p$it" }
        }


    override suspend fun getInventory(vendor: String, part: String): Int =
        withContext(dispatcher) {
            delay(1000) // "can be a blocking operation"
            println("Inventory $vendor $part")
//            if (part.contains("2"))
//                throw RuntimeException("Could not get inventory for vendor $vendor, part: $part")
            1
        }

    override suspend fun updateVendorParts(vendor: String, part: String, inv: Int): Boolean =
        withContext(dispatcher) {
            println("Updated $vendor, $part -> $inv")
            delay(1000)// Thread.sleep(1000L) // can be a blocking operation
            updateInventoryCounter()
            part.removePrefix("p").toInt() % 2 == 0
        }

    private suspend fun updateInventoryCounter()  {
        mutex.withLock {
            counter++
        }
    }
}

// Dispatchers.IO is only needed if you have an API that blocks threads.
// You do not need to use Dispatchers.IO if you want to use a network or database library
// that provides suspending functions.