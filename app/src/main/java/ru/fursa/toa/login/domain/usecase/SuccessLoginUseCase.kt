package ru.fursa.toa.login.domain.usecase

import ru.fursa.toa.login.domain.model.Credentials
import ru.fursa.toa.login.domain.model.LoginResult

class SuccessLoginUseCase : CredentialsLoginUseCase {

    override suspend fun invoke(credentials: Credentials): LoginResult {
        return LoginResult.Success("")
    }
}