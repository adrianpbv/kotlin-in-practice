package f_205_job.s_15

import kotlinx.coroutines.*

suspend fun main(): Unit = coroutineScope {
  val parentJob = Job()
  val job = Job(parentJob)// Pass a reference to the parent as an argument of the Job function.
  launch(job) {
      delay(1000)
      println("Text 1")
  }
  launch(job) {
      delay(2000)
      println("Text 2")
  }
  delay(1100)
  parentJob.cancel()// new jobs will be cancelled when the parent is.
  job.children.forEach { it.join() }
}

/**
 * Every coroutine has its own job, which is the only context not inherited from the parent.
 * A job from an argument or parent coroutine is used as a parent of this new job.
 */
