package com.sarathexp.offlinewallet.util

import kotlin.random.Random
//TODO:
interface Consumer {
    val key
        get() = Random.nextLong(0, 100000000000000)
}

sealed interface Event : Consumer {
    data object Alert : Event
}

