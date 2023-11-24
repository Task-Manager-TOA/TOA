package ru.fursa.toa.login.domain.repository

import ru.fursa.toa.core.data.Result
import ru.fursa.toa.login.domain.model.AuthToken
import ru.fursa.toa.login.domain.model.Credentials
import ru.fursa.toa.login.domain.model.LoginResponse
import ru.fursa.toa.login.domain.model.RefreshToken
import ru.fursa.toa.login.domain.model.Token
import java.util.UUID
import javax.inject.Inject

class DemoLoginService @Inject constructor(): LoginRepository {
    override suspend fun login(credentials: Credentials): Result<LoginResponse> {
        val defaultAuthToken = AuthToken(value = UUID.randomUUID().toString())
        val defaultRefreshToken = RefreshToken(value = UUID.randomUUID().toString())
        val currentToken = Token(authToken = defaultAuthToken, refreshToken = defaultRefreshToken)
        val defaultResponse = LoginResponse(token = currentToken)
        return Result.Success(defaultResponse)
    }
}