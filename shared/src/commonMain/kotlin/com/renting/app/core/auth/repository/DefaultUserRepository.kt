package com.renting.app.core.auth.repository

import com.arkivanov.mvikotlin.logging.logger.DefaultLogger
import com.renting.app.core.auth.model.UserInfo
import com.renting.app.core.model.Image
import com.renting.app.core.monad.Either
import com.renting.app.core.monad.left
import com.renting.app.core.monad.right
import com.renting.app.core.network.getOrLeft
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
        val result = httpClient.getOrLeft("/api/users/current") {
            contentType(ContentType.Application.Json)
        }
        val response = when (result) {
            is Either.Left -> return@withContext result.error.left()
            is Either.Right -> result.value
        }

        if (response.status.isSuccess()) {
            val userResponse = response.body<UserResponse>()
            UserInfo(
                login = userResponse.login,
                firstName = userResponse.firstName,
                lastName = userResponse.lastName,
                avatar = userResponse.imagePathWithoutHost?.let(Image::Url),
            ).right()
        } else {
            val errorResponse = response.body<UserErrorResponse>()
            val errorMessage = errorResponse.message
            DefaultLogger.log(errorMessage)
            Exception(errorMessage).left()
        }
    }
}

@Serializable
private class UserResponse(
    @SerialName("username")
    val login: String,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("imagePath")
    val imagePathWithoutHost: String?,
)

@Serializable
private class UserErrorResponse(
    @SerialName("message")
    val message: String,
)
