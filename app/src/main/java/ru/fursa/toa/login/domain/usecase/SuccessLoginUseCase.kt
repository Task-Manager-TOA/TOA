package ru.fursa.toa.login.domain.usecase

import ru.fursa.toa.login.domain.model.LoginResult
import ru.fursa.toa.login.domain.model.LoginType
import java.util.UUID

/**
 * Temporary implementation of Login use case [LoginUseCase] for debugging purposes.
 * This should be replaced by API calls
 */
class SuccessLoginUseCase : LoginUseCase {
    override suspend fun login(loginType: LoginType): LoginResult {
        val authToken = UUID.randomUUID().toString()

        when (loginType) {
            is LoginType.Credentials -> Unit
            LoginType.Google -> Unit
        }
        return LoginResult.Success(authToken = authToken)
    }
}