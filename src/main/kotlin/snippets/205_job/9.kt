package f_205_job.s_9

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    launch {
        delay(1000)
        println("Test1")
    }
    launch {
        delay(2000)
        println("Test2")
    }

    val children = coroutineContext[Job]
        ?.children

    val childrenNum = children?.count()
    println("Number of children: $childrenNum")
    children?.forEach { it.join() } // wait for each child
    println("All tests are done")
}

// Number of children: 2
// (1 sec)
// Test1
// (1 sec)
// Test2
// All tests are done

/**
 * It is not uncommon to use join to synchronize coroutines.
 *
 * suspend fun completeOrder(order: Order) = coroutineScope { val createOrderJob = launch {
 *         orderService.createOrder(order)
 *     }
 *      val invoiceJob = launch {
 *      val invoiceId = invoiceService.createInvoice(order) createOrderJob.join() orderService.markOrderAsInvoiced(order, invoiceId)
 *          }
 *      val deliveryJob = launch {
 *      val deliveryId = deliveryService.orderDelivery(order) invoiceJob.join() orderService.markOrderAsDelivered(order, deliveryId)
 *          }
 *     invoiceJob.join()
 *     deliveryJob.join()
 *     sendEmail(order)
 * }
 *
 * Instead of using join, you might also use await from async to wait for the result of a coroutine.
 * The only difference is that await returns the result of the coroutine, while join returns Unit.
 */