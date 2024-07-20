package f_207_exception_handling.s_7

import kotlinx.coroutines.*

class MyException : Throwable()

/**
 * When we call await on Deferred, it should return the result of this coroutine if the coroutine finished
 * successfully, or it should throw an exception if the coroutine ended with an exception
 */

suspend fun main() = supervisorScope {
    val str1 = async<String> {
        delay(1000)
        throw MyException()
    }

    val str2 = async {
        delay(2000)
        "Text2"
    }

    try {
        println(str1.await()) // we also need to catch the exception when calling await.
    } catch (e: MyException) {
        println(e)
    }

    println(str2.await())
}
