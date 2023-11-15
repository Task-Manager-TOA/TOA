package ru.fursa.toa.login.ui

import ru.fursa.toa.login.domain.model.Credentials

data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val showProgressBar: Boolean = false,
    val errorMessage: String? = null,
    val emailInputErrorMessage: String? = null,
    val passwordInputErrorMessage: String? = null,
)

sealed class LoginState {
    object Initial : LoginState()
    data class Active(
        val credentials: Credentials
    ) : LoginState()

    data class Submitting(
        val credentials: Credentials
    ) : LoginState()

    data class SubmissionError(
        val credentials: Credentials,
        val errorMessage: String,
    ) : LoginState()

    data class InputError(
        val credentials: Credentials,
        val emailInputErrorMessage: String?,
        val passwordInputErrorMessage: String?,
    )
}