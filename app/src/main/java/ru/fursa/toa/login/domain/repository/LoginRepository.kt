package ru.fursa.toa.login.domain.repository

import ru.fursa.toa.core.data.Result
import ru.fursa.toa.login.domain.model.Credentials
import ru.fursa.toa.login.domain.model.LoginResponse

interface LoginRepository {
    suspend fun login(
        credentials: Credentials
    ): Result<LoginResponse>
}