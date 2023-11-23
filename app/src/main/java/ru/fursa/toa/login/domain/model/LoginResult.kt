package ru.fursa.toa.login.domain.model
sealed class LoginResult {
    data class Success(val authToken: String): LoginResult()
    sealed class Failure: LoginResult() {
        object InvalidCredentials: Failure()
        object EmptyCredentials: Failure()
        object Unknown: Failure()
    }
}
