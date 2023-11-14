package ru.fursa.toa.fakes

import io.mockk.coVerify
import io.mockk.mockk
import ru.fursa.toa.login.domain.model.Token
import ru.fursa.toa.login.domain.repository.TokenRepository

class FakeTokenRepository {

    val mock: TokenRepository = mockk(
        relaxUnitFun = true,
    )

    fun verifyTokenStored(token: Token) {
        coVerify {
            mock.storeToken(token)
        }
    }

    fun verifyNoTokenStored() {
        coVerify(exactly = 0) {
            mock.storeToken(any())
        }
    }
}