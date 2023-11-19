package ru.fursa.toa.login.ui.login_screen

import ru.fursa.toa.login.domain.model.Credentials

sealed class LoginViewState(
    open val credentials: Credentials,
    open val buttonsEnabled: Boolean = true,
) {
    object Initial : LoginViewState(
        credentials = Credentials(),
    )
    data class Active(
        override val credentials: Credentials
    ) : LoginViewState(
        credentials = credentials,
        buttonsEnabled = true
    )

    data class Submitting(
        override val credentials: Credentials
    ) : LoginViewState(
        credentials = credentials,
        buttonsEnabled = false
    )

    data class SubmissionError(
        override val credentials: Credentials,
        val errorMessage: String,
    ) : LoginViewState(
        credentials = credentials,
        buttonsEnabled = false
    )

    data class InputError(
        override val credentials: Credentials,
        val emailInputErrorMessage: String?,
        val passwordInputErrorMessage: String?,
    ): LoginViewState(
        credentials = credentials,
        buttonsEnabled = false
    )
}