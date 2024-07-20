package adrian.refresher

import kotlinx.coroutines.*
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.coroutines.ContinuationInterceptor
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class InventoryRefresherTest {

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `should run in parallel`() = runTest {
        val testDispatcher = this.coroutineContext[CoroutineDispatcher] ?: StandardTestDispatcher()
        val repository = RefresherRepository(testDispatcher)
        val service = InventoryService(repository)

        service.refreshInventory()

        assertEquals(4000, currentTime)
        assertEquals(4, repository.counter)
    }

    @Test
    fun test2() = runTest {
        assertEquals(0, currentTime)
        coroutineScope {
        launch { delay(1000) }
        launch { delay(1500) }
        launch { delay(2000) }
    }
        assertEquals(2000, currentTime)
    }

    @Test
    fun test3() = runTest {
        assertEquals(0, currentTime)
        coroutineScope {
            delay(1000) // getVendor \\ 1s
            delay(1000) // getPartList \\ 1s
            repeat(5) {
                launch {
                    val inv = async {
                        delay(1000) // getInventory \\ 1s
                        123
                    }
                    inv.await() // await inventory
                    delay(1000) // update inventory \\ 1s
                }
            }
        }
        assertEquals(4000, currentTime) // time per vendor
    }
}