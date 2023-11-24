package ru.fursa.toa.login.domain.repository

import ru.fursa.toa.login.domain.model.AuthToken
import ru.fursa.toa.login.domain.model.RefreshToken
import ru.fursa.toa.login.domain.model.Token
import java.util.UUID
import javax.inject.Inject

class DemoTokenService @Inject constructor(): TokenRepository {
    override suspend fun storeToken(token: Token) = Unit

    override suspend fun fetchToken(): Token? = Token(
        AuthToken(value = UUID.randomUUID().toString()),
        refreshToken = RefreshToken(value = UUID.randomUUID().toString())
    )
}