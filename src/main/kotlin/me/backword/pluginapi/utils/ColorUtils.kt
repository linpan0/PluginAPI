package me.backword.pluginapi.utils

fun String.colorize() = replace('&', '§')
fun List<String>.colorize() = map { it.colorize() }
fun Array<String>.colorize() = map { it.colorize() }.toTypedArray()