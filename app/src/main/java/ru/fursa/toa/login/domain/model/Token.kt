package ru.fursa.toa.login.domain.model

@JvmInline
value class AuthToken(val value: String)

@JvmInline
value class RefreshToken(val value: String)
data class Token(
    val authToken: AuthToken,
    val refreshToken: RefreshToken,
)
