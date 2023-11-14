package ru.fursa.toa.fakes

import io.mockk.coEvery
import io.mockk.mockk
import ru.fursa.toa.core.data.Result
import ru.fursa.toa.login.domain.model.Credentials
import ru.fursa.toa.login.domain.model.LoginResponse
import ru.fursa.toa.login.domain.repository.LoginRepository

/**
 * Fake implementation of [LoginRepository]
 */
class FakeLoginRepository {
    val mock: LoginRepository = mockk()

    fun mockLoginWithCredentials(
        credentials: Credentials,
        result: Result<LoginResponse>,
    ) {
        coEvery {
            mock.login(credentials)
        } returns result
    }
}