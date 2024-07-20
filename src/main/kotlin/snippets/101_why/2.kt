package f_101_why.s_2

import kotlinx.coroutines.*

fun main() = runBlocking {
  repeat(100_000) {
      launch {
          delay(1000L)
          print(".")
      }
  }
}

/**
 * when you run it, the program will wait for a second and then print all the dots. The
 * cost of starting all these coroutines is so cheap that it is barely noticeable.
 */
