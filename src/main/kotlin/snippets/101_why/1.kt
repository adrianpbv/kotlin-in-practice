package f_101_why.s_1

import kotlin.concurrent.thread

fun main() {
  repeat(100_000) {
      thread {
          Thread.sleep(1000L)
          print(".")
      }
  }
}
/**
 * Blocking whenever you wait for a response adds up to a significant cost in memory and processor use (for the creation,
 * maintenance, and synchronization of these threads).
 *
 * you will see
 * it takes a while to print all those dots, or it will break with an OutOfMemoryError
 * exception. This is the cost of running so many threads.
 */
