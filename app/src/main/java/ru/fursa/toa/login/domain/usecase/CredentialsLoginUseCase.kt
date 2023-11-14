package ru.fursa.toa.login.domain.usecase

import ru.fursa.toa.login.domain.model.Credentials
import ru.fursa.toa.login.domain.model.LoginResult

interface CredentialsLoginUseCase {
    suspend fun invoke(credentials: Credentials): LoginResult
}