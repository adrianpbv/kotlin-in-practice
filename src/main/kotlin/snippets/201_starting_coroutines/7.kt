package f_201_starting_coroutines.s_7

import kotlinx.coroutines.*

/**
 * The parent-child relationship has a couple of important consequences:
 * 1. Children inherit context from their parent (but they can also overwrite it).
 * 2. A parent cannot complete until all its children have completed.
 * 3. When the parent is cancelled,its child coroutines are cancelled too.
 * 4. When a child completes with an exception, this exception is passed to the parent.
 */
// when runBlocking is a parent, it must wait for all its children to complete
fun main() = runBlocking {
    // this launch coroutines that uses the same parent scope of runBlocking
    launch { // same as this.launch
        delay(1000L)
        println("World!")
    }
    launch { // same as this.launch
        delay(1000L)
        println("World!")
    }
    launch { // same as this.launch
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}
// Hello,
// (1 sec)
// World!
// World!
// World!