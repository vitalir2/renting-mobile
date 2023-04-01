package com.renting.app.android.core.image

import coil.intercept.Interceptor
import coil.request.ImageResult
import okhttp3.Headers

class AuthImageInterceptor(
    private val getAuthToken: () -> String?,
) : Interceptor {

    override suspend fun intercept(chain: Interceptor.Chain): ImageResult {
        val token = getAuthToken() ?: return chain.proceed(chain.request)

        val currentHeaders = chain.request.headers
        val newHeaders = Headers.Builder()
            .add("Authorization", token)
            .build()

        val mergedHeaders = currentHeaders.newBuilder()
            .addAll(newHeaders)
            .build()

        val updatedRequest = chain.request.newBuilder()
            .headers(mergedHeaders)
            .build()
        return chain.proceed(updatedRequest)
    }
}
