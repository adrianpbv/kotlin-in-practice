package f_201_starting_coroutines.s_4

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

suspend fun main() {
    val value = GlobalScope.async {
        delay(2000L)
        1
    }
    println("Calculating")
    // If we call await on an already completed Deferred, then there is no suspending;
    // await just returns the value. This is why calling await multiple times in succession
    // does not suspend multiple times.
    print(value.await())
    print(value.await())
    print(value.await())
}
// Calculating
// (2 sec)
// 111

// The async builder is often used to asynchronously execute two processes and then
// combine their results.
// scope.launch {
//  val news = async { newsRepo.getNews() }
//  val newsSummary = async { newsRepo.getNewsSummary() }
//  view.showNews(newsSummary.await(), news.await())
//}
