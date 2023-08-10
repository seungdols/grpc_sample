package com.seungdols.dev.grpc.sample.hello

import kotlin.random.Random

fun generate1MBString(): String {
    val alphabet = "abcdefghijklmnopqrstuvwxyz"

    val builder = StringBuilder()
    for (i in 0 until 1_000_000) {
        builder.append(Random.nextInt(alphabet.length).let { alphabet[it] })
    }
    return builder.toString()
}
