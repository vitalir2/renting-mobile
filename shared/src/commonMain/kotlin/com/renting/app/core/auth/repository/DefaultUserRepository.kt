package com.renting.app.core.auth.repository

import com.renting.app.core.auth.model.UserInfo
import com.renting.app.core.monad.Either
import com.renting.app.core.monad.left
import com.renting.app.core.monad.right
import com.renting.app.core.utils.Environment
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

internal class DefaultUserRepository(
    private val httpClient: HttpClient,
    private val ioDispatcher: CoroutineDispatcher,
) : UserRepository {

    override suspend fun getUserInfo(): Either<Exception, UserInfo> = withContext(ioDispatcher) {
        val response = httpClient.get("/api/users/current") {
            contentType(ContentType.Application.Json)
        }
        if (response.status.isSuccess()) {
            val userResponse = response.body<UserResponse>()
            UserInfo(
                login = userResponse.login,
                imageUrl = "${Environment.PRODUCTION_NETWORK_HOST}${userResponse.imagePathWithoutHost}",
            ).right()
        } else {
            val errorResponse = response.body<UserErrorResponse>()
            Exception(errorResponse.message).left()
        }
    }
}

@Serializable
private class UserResponse(
    @SerialName("username")
    val login: String,
    @SerialName("imagePath")
    val imagePathWithoutHost: String,
)

@Serializable
private class UserErrorResponse(
    @SerialName("message")
    val message: String,
)
