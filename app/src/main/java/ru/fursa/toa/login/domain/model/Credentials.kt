package ru.fursa.toa.login.domain.model
@JvmInline
value class Email(val email: String)
@JvmInline
value class Password(val password: String)

data class Credentials(
    val email: Email,
    val password: Password
)