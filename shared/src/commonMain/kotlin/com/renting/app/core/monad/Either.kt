package com.renting.app.core.monad

import kotlinx.coroutines.CancellationException

sealed interface Either<out TLeft, out TRight> {

    data class Left<T>(val error: T) : Either<T, Nothing>

    data class Right<T>(val value: T) : Either<Nothing, T>
}

fun <T> T.left(): Either.Left<T> = Either.Left(this)

fun <T> T.right(): Either.Right<T> = Either.Right(this)

@Suppress("UNCHECKED_CAST")
inline fun <TLeft, TRight> runEither(block: () -> TRight): Either<TLeft, TRight> {
    return try {
        block().right()
    } catch (exception: CancellationException) {
        throw exception
    } catch (throwable: Throwable) {
        (throwable as TLeft).left()
    }
}
