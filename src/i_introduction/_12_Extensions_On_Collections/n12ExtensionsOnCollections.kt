package i_introduction._12_Extensions_On_Collections

import util.TODO
import util.doc12
import java.util.*
import kotlin.collections.ArrayList

fun todoTask12(): Nothing = TODO(
    """
        Task 12.
        Kotlin standard library contains lots of extension functions that make the work with collections more convenient.
        Rewrite the previous example once more using an extension function 'sortedDescending'.

        Kotlin code can be easily mixed with Java code.
        Thus in Kotlin we don't introduce our own collections, but use standard Java ones (slightly improved).
        Read about read-only and mutable views on Java collections.
    """,
    documentation = doc12()
)

fun task12(): List<Int> {
    return arrayListOf(1, 5, 2).sortedDescending()
}

fun Collections.sortedDescending(arrayList: ArrayList<Int>) = Collections.sort(arrayList, { x, y -> y - x })


