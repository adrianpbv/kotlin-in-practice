package f_305_flow_introduction.s_2

import java.io.File
import java.math.BigInteger

/**
 * Sequence's forEach is a blocking operation
 * This is why a coroutine started on the same thread with launch will wait,
 * so one coroutine’s execution blocks another’s.
 *
 * This is a case where we should use Flow instead of Sequence.
 */

fun getSequence(): Sequence<String> = sequence {
   repeat(3) {
       Thread.sleep(1000)
       yield("User$it")
   }
}

fun main() {
   val list = getSequence()
   println("Function started")
   list.forEach { println(it) }
}

// Sequences are perfect for sources of data whose size might be big (or infinite)
// and elements might be heavy, so we want to calculate or read them on demand, lazily.
val fibonacci: Sequence<BigInteger> = sequence {
    var first = 0.toBigInteger()
    var second = 1.toBigInteger()
    while (true) {
        yield(first)
        val temp = first
        first += second
        second = temp
    }
}
fun countCharactersInFile(path: String): Int = File(path).useLines { lines ->
    lines.sumBy { it.length }
}

// iterator function in Sequence is not suspend.4