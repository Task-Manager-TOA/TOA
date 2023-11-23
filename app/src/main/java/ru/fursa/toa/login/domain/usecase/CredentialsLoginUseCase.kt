package ru.fursa.toa.login.domain.usecase

import ru.fursa.toa.login.domain.model.Credentials
import ru.fursa.toa.login.domain.model.LoginResult

interface CredentialsLoginUseCase {
    suspend operator fun invoke(credentials: Credentials): LoginResult
}