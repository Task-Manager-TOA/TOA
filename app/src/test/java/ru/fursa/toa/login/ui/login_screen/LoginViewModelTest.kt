package ru.fursa.toa.login.ui.login_screen

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import ru.fursa.toa.core.utils.UiText
import ru.fursa.toa.login.domain.model.Credentials
import ru.fursa.toa.login.domain.model.Email
import ru.fursa.toa.login.domain.model.LoginResult
import ru.fursa.toa.login.domain.model.Password

class LoginViewModelTest {
    private lateinit var testRobot: LoginViewModelRobot

    private val defaultCredentials = Credentials(
        Email(value = "test@mail.com"),
        Password(value = "1234567890")
    )
    @Before
    fun setUp() {
        testRobot = LoginViewModelRobot()
    }

    @Test
    fun testInitialState() {
        testRobot
            .buildViewModel()
            .assertViewState(LoginViewState.Initial)
    }

    @Test
    fun testUpdateCredentials() {
        val credentials = defaultCredentials
        testRobot.buildViewModel()
            .enterEmail(credentials.email.value)
            .enterPassword(credentials.password.value)
            .assertViewState(LoginViewState.Active(credentials))
    }

    @Test
    fun testInvalidCredentialLogin() {
        val credentials = defaultCredentials
        testRobot.buildViewModel()
            .enterEmail(credentials.email.value)
            .enterPassword(credentials.password.value)
            .clickLoginButton()
            .assertViewState(LoginViewState.Submitting(credentials))
            .mockLoginForCredentials(credentials, LoginResult.Failure.InvalidCredentials)
            .assertViewState(LoginViewState.SubmissionError(
                credentials = defaultCredentials,
                errorMessage = UiText.StringText("Invalid credentials error")
            ))
    }

}