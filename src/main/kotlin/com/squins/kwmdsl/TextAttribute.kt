package com.squins.kwmdsl

fun attr(name: String, value: Boolean) = name to Text(value.toString())

fun attr(name: String, value: Byte) = name to Text(value.toString())

fun attr(name: String, value: Int) = name to Text(value.toString())

fun attr(name: String, value: Long) = name to Text(value.toString())

fun attr(name: String, value: Short) = name to Text(value.toString())

fun attr(name: String, value: String) = name to Text(value)
