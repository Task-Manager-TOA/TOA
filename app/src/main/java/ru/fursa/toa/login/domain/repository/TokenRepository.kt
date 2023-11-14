package ru.fursa.toa.login.domain.repository

import ru.fursa.toa.login.domain.model.Token

interface TokenRepository {
    suspend fun storeToken(
        token: Token,
    )

    suspend fun fetchToken(): Token?
}
