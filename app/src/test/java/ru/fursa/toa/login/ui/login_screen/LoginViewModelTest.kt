package ru.fursa.toa.login.ui.login_screen

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import ru.fursa.toa.login.domain.model.Credentials
import ru.fursa.toa.login.domain.model.Email
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

}