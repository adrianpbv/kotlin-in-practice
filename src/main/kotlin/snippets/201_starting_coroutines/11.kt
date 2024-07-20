package f_201_starting_coroutines.s_11

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

/**
 * Coroutine scope functions (coroutineScope) (CSF) and asynchronous coroutine builders (async, launch) (ACB)
 * They both start coroutines, while the first is suspending and the others are async.
 * CSF do not require a scope, while coroutine builders are regular functions that do require a scope.
 *
 * In practice, CSF are used to create a scope for asynchronous coroutines.
 */

suspend fun main() = coroutineScope {
    println("A")
    val a: Deferred<Int> = async {
        delay(1000L)
        10
    }
    println("B")
    val b: Deferred<Int> = async {
        delay(1000L)
        20
    }
    println("C")
    println(a.await() + b.await())
}
// A
// B
// C
// (2 sec)
// 30

// Coroutine builders can be started in lambda expressions (.map { } ):
// suspend fun getPublicUserDetails( userId: String
// ): List<ArticleDetails> = coroutineScope {
//    articleRepo.getArticles(userId)
//        .filter { it.isPublic }
//        .map { async { getArticleDetails(it.id) } }
//        .awaitAll() // suspends until all the results are ready
//}

/** CSF maintain structured concurrency
 * Which has some consequences :
 * 1. inherit context from their parent,
 * 2. cannot complete until all their children are completed,
 * 3. cancel their children when they get cancelled,
 * 4. cancel and throw an exception when they receive an exception from a child
 */