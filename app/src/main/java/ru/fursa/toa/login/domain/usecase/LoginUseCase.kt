package ru.fursa.toa.login.domain.usecase

import ru.fursa.toa.core.data.DataResponse
import ru.fursa.toa.login.domain.model.LoginResult


@JvmInline
value class Email(val email: String)

@JvmInline
value class Password(val password: String)

interface LoginUseCase {
    suspend fun login(email: Email, password: Password): LoginResult
}