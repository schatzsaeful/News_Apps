package com.contoh.newsapps.utils.ext

fun String?.orEmpty(): String = this ?: ""
fun Long?.orZero(): Long = this ?: 0
