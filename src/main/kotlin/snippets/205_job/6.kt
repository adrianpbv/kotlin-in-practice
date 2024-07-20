package f_205_job.s_6

import kotlinx.coroutines.*

/**
 * runBlocking does not wait for launch because it has no relation with it.
 * This is because launch uses the job from the argument as a parent.
 * When a coroutine has its own (independent) job, it has nearly no relation to its parent.
 */

fun main(): Unit = runBlocking {
  launch(Job()) { // the new job replaces one from parent
      delay(1000)
      println("Will not be printed")
  }
}

// This causes us to lose structured concurrency,
// which is a problematic situation that should be avoided.
