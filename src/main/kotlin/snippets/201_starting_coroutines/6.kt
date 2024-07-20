package f_201_starting_coroutines.s_6

import kotlinx.coroutines.runBlocking
annotation class Test

fun main() = runBlocking {
    // ...
}

class MyTests {
    // use runTest
    @Test
    fun `a test`() = runBlocking {

    }
}

// Sumary runBlocking code builder:
// runBlocking is
// Used only in places in our program where we do want to block the thread, such
// as when we need a regular function to call suspending functions and await their
// completion.
// fun runDataMigrationScript() = runBlocking {
//  val sourceData = readDataFromSource() // suspend function
//  val transformedData = transformData(sourceData)
//  writeDataToTarget(transformedData)
//}
