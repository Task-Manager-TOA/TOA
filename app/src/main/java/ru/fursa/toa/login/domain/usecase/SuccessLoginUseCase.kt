package ru.fursa.toa.login.domain.usecase

import ru.fursa.toa.login.domain.model.LoginResult
import java.util.UUID

/**
 * Temporary implementation of Login use case [LoginUseCase] for debugging purposes.
 * This should be replaced by API calls
 */
class SuccessLoginUseCase: LoginUseCase {
    override suspend fun login(email: Email, password: Password): LoginResult {
        val authToken = UUID.randomUUID().toString()
        return LoginResult.Success(authToken = authToken)
    }
}