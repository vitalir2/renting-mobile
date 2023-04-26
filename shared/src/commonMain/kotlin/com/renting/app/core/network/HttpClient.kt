package com.renting.app.core.network

import com.renting.app.core.monad.Either
import com.renting.app.core.monad.runEither
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

internal suspend inline fun HttpClient.getOrLeft(
    urlString: String,
    block: HttpRequestBuilder.() -> Unit,
): Either<Exception, HttpResponse> = runEither {
    get(urlString, block)
}

internal suspend inline fun HttpClient.postOrLeft(
    urlString: String,
    block: HttpRequestBuilder.() -> Unit,
): Either<Exception, HttpResponse> = runEither {
    post(urlString, block)
}
