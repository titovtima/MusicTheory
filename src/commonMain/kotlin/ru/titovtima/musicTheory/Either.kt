package ru.titovtima.musicTheory

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
sealed class Either<out L, out R> {
    data class Left<out L, out R>(val value: L) : Either<L, R>()
    data class Right<out L, out R>(val value: R) : Either<L, R>()
}

fun <E> E.eitherLeft() = Either.Left<E, Nothing>(this)
fun <T> T.eitherRight() = Either.Right<Nothing, T>(this)