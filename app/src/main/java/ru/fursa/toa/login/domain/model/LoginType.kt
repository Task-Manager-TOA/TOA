package ru.fursa.toa.login.domain.model

import ru.fursa.toa.login.domain.usecase.Email
import ru.fursa.toa.login.domain.usecase.Password

sealed class LoginType {
    data class Credentials(
        val email: Email,
        val password: Password,
    ): LoginType()

    object Google: LoginType()
}
