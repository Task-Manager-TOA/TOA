package ru.fursa.toa.login.ui.login_screen

import io.mockk.coEvery
import io.mockk.mockk
import ru.fursa.toa.login.domain.model.Credentials
import ru.fursa.toa.login.domain.model.LoginResult
import ru.fursa.toa.login.domain.usecase.CredentialsLoginUseCase

class FakeCredentialsLoginUseCase {

    val mock: CredentialsLoginUseCase = mockk()

    fun mockLoginResult(
        credentials: Credentials,
        loginResult: LoginResult,
    ) {
        coEvery {
            mock.invoke(credentials)
        } returns loginResult
    }
}