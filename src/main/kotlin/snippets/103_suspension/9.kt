package f_103_suspension.s_9

import kotlin.concurrent.thread
import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

data class User(val name: String)

// passing in the callback function
fun requestUser(callback: (User) -> Unit) {
    thread {
        Thread.sleep(1000)
        callback.invoke(User("Test"))
    }
}
// how to turn a callback function into a suspending function
suspend fun requestUser(): User {
    return suspendCancellableCoroutine<User> { cont ->
        requestUser { user ->
            cont.resume(user)
        }
    }
}

suspend fun main() {
    println("Before")
    val user = requestUser()
    println(user)
    println("After")
}

// Return data with exception treatment
data class Response (val isSuccessful: Boolean, val data: User)
data class ApiException(val code: Int, override val message: String) : Throwable()

fun requestUser2(callback: (Response) -> Unit) {
    thread {
        Thread.sleep(1000)
        callback.invoke(Response(true, User("Test")))
    }
}

suspend fun requestUser2(): User {
    return suspendCancellableCoroutine<User> { cont ->
        requestUser2 { resp ->
            if (resp.isSuccessful) {
                cont.resume(resp.data)
            } else {
                val e = ApiException(
                    123,
                   "Message"
                )
                cont.resumeWithException(e)
            }
        }
    }
}
