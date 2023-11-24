package ru.fursa.toa.login.presentation.login_screen

import io.mockk.coEvery
import io.mockk.mockk
import ru.fursa.toa.login.domain.model.Credentials
import ru.fursa.toa.login.domain.model.LoginResult
import ru.fursa.toa.login.domain.usecase.CredentialsLoginUseCase

class FakeCredentialsLoginUseCase {

    val mock: CredentialsLoginUseCase = mockk()

    fun mockLoginResultForCredentials(
        credentials: Credentials,
        result: LoginResult,
    ) {
        coEvery {
            mock(credentials)
        } returns result
    }
}