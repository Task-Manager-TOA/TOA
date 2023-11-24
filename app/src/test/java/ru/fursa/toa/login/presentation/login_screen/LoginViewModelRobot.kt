package ru.fursa.toa.login.presentation.login_screen

import app.cash.turbine.test
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.fursa.toa.login.domain.model.Credentials
import ru.fursa.toa.login.domain.model.LoginResult
import ru.fursa.toa.login.presentation.LoginViewModel
import ru.fursa.toa.login.presentation.LoginViewState

class LoginViewModelRobot {
    private val fakeCredentialsLoginUseCase = FakeCredentialsLoginUseCase()
    private lateinit var viewModel: LoginViewModel

    fun buildViewModel() = apply {
        viewModel = LoginViewModel(
            credsUseCase = fakeCredentialsLoginUseCase.mock
        )
    }

    fun mockLoginForCredentials(credentials: Credentials, result: LoginResult) = apply {
        fakeCredentialsLoginUseCase.mockLoginResultForCredentials(credentials, result)
    }

    fun enterEmail(email: String) = apply {
        viewModel.emailChanged(email)
    }

    fun enterPassword(password: String) = apply {
        viewModel.passwordChanged(password)
    }

    fun clickLoginButton() = apply {
        viewModel.loginButtonClicked()
    }

    fun clickSignUpButton() = apply {
        viewModel.signUpButtonClicked()
    }

    fun assertViewState(expectedViewState: LoginViewState) = apply {
        assert(viewModel.viewState.value == expectedViewState)
    }

    suspend fun expectViewStates(
        action: LoginViewModelRobot.() -> Unit,
        viewStates: List<LoginViewState>,
    ) = runBlocking {
       launch {
           viewModel.viewState.test {
               action()

               for (state in viewStates) {
                   assert(awaitItem() == state)
               }

           }
       }
    }
    suspend fun expectViewStates(viewStates: List<LoginViewState>) = runBlocking {
        launch {
            viewModel.viewState.test {
                for (state in viewStates) {
                    assert(awaitItem() == state)
                }

                this.cancel()
            }
        }
    }

}