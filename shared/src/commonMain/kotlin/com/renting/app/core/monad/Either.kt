package com.renting.app.core.monad

sealed interface Either<out TLeft, out TRight> {

    data class Left<T>(val error: T) : Either<T, Nothing>

    data class Right<T>(val value: T) : Either<Nothing, T>
}

fun <T> T.left(): Either.Left<T> = Either.Left(this)

fun <T> T.right(): Either.Right<T> = Either.Right(this)
