package ru.fursa.toa.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.fursa.toa.R
import ru.fursa.toa.core.utils.UiText
import ru.fursa.toa.login.domain.model.Credentials
import ru.fursa.toa.login.domain.model.Email
import ru.fursa.toa.login.domain.model.LoginResult
import ru.fursa.toa.login.domain.model.Password
import ru.fursa.toa.login.domain.usecase.CredentialsLoginUseCase

class LoginViewModel(
    private val loginUseCase: CredentialsLoginUseCase
) : ViewModel() {
    private val _viewState: MutableStateFlow<LoginViewState> =
        MutableStateFlow(LoginViewState.Initial)
    val viewState: StateFlow<LoginViewState> = _viewState.asStateFlow()

    fun emailChanged(email: String) {
        val currentCredentials = _viewState.value.credentials
        _viewState.value = LoginViewState.Active(
            credentials = currentCredentials.withUpdatedEmail(email)
        )
    }

    fun passwordChanged(password: String) {
        val currentCredentials = _viewState.value.credentials
        _viewState.value = LoginViewState.Active(
            credentials = currentCredentials.withUpdatedPassword(password)
        )
    }

    fun loginButtonClicked() {
        val currentCredentials = _viewState.value.credentials

        _viewState.value = LoginViewState.Submitting(
            credentials = currentCredentials,
        )

        viewModelScope.launch {
            val loginResult = loginUseCase(currentCredentials)

            _viewState.value = when (loginResult) {
                is LoginResult.Failure.InvalidCredentials -> {
                    LoginViewState.SubmissionError(
                        credentials = currentCredentials,
                        errorMessage = UiText.ResourceText(R.string.error_invalid_creds),
                    )
                }
                is LoginResult.Failure.Unknown -> {
                    LoginViewState.SubmissionError(
                        credentials = currentCredentials,
                        errorMessage = UiText.ResourceText(R.string.unknown_error),
                    )
                }
                is LoginResult.Failure.EmptyCredentials -> {
                    LoginViewState.Active(
                        credentials = currentCredentials,
                        emailInputErrorMessage = UiText.ResourceText(R.string.err_empty_email),
                        passwordInputErrorMessage = UiText.ResourceText(R.string.err_empty_password),
                    )
                }
                else -> _viewState.value
            }
        }
    }

    fun signUpButtonClicked() {
        TODO()
    }
}

private fun Credentials.withUpdatedEmail(email: String): Credentials {
    return this.copy(email = Email(value = email))
}

private fun Credentials.withUpdatedPassword(password: String): Credentials {
    return this.copy(password = Password(value = password))
}