package adrian.refresher

import kotlinx.coroutines.*

class InventoryService(
    private val refresherRepository: IRefresherRepository
) {
    suspend fun refreshInventory() : Boolean = coroutineScope {
        val vendors = refresherRepository.getVendors()
        vendors.forEach { vendor ->
            launch { // asynchronous coroutine builder
                println("got vendor: $vendor")
                refresherRepository.getVendorParts(vendor).forEach { part ->
                    launch {
                        println("$vendor got part: $part")
                        try {
                            val res = refresherRepository.getInventory(vendor, part)
                            yield()
                            refresherRepository.updateVendorParts(vendor, part, res)
                        } catch (e: Exception) {
                            println("Exception caught $e")
                        }
                    }
                }
                yield() // to allow cancellation and redispatching between blocking functions
            }
        }
        true
    }
}