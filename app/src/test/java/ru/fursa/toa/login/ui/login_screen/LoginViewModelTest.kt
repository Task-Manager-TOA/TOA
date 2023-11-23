package ru.fursa.toa.login.ui.login_screen

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import ru.fursa.toa.R
import ru.fursa.toa.core.utils.UiText
import ru.fursa.toa.login.domain.model.Credentials
import ru.fursa.toa.login.domain.model.Email
import ru.fursa.toa.login.domain.model.LoginResult
import ru.fursa.toa.login.domain.model.Password

class LoginViewModelTest {
    private lateinit var testRobot: LoginViewModelRobot

    private val dispatcher = TestCoroutineDispatcher()

    private val defaultCredentials = Credentials(
        Email(value = "test@mail.com"),
        Password(value = "1234567890")
    )
    @Before
    fun setUp() {
        testRobot = LoginViewModelRobot()
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
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
    fun testSubmitInvalidCredentials() = runBlockingTest {
        val testEmail = "ifv93@mail.com"
        val testPassword = "1234567890"
        val completedCredentials = Credentials(
            email = Email(testEmail),
            password = Password(testPassword),
        )

        val initialState = LoginViewState.Initial
        val emailEnteredState = LoginViewState.Active(
            credentials = Credentials(email = Email(testEmail))
        )
        val emailPasswordEnteredState = LoginViewState.Active(
            credentials = completedCredentials,
        )
        val submittingState = LoginViewState.Submitting(
            credentials = completedCredentials,
        )
        val submissionErrorState = LoginViewState.SubmissionError(
            credentials = completedCredentials,
            errorMessage = UiText.ResourceText(R.string.error_invalid_creds)
        )

        val expectedViewStates = listOf(
            initialState,
            emailEnteredState,
            emailPasswordEnteredState,
            submittingState,
            submissionErrorState,
        )

        testRobot
            .buildViewModel()
            .mockLoginForCredentials(
                credentials = completedCredentials,
                result = LoginResult.Failure.InvalidCredentials,
            )
            .expectViewStates(
                action = {
                    enterEmail(testEmail)
                    enterPassword(testPassword)
                    clickLoginButton()
                },
                viewStates = expectedViewStates,
            )
    }

    @Test
    fun testUnknownLoginFailure() = runBlockingTest {
        val testEmail = "ifv93@mail.com"
        val testPassword = "1234567890"
        val completedCredentials = Credentials(
            email = Email(testEmail),
            password = Password(testPassword),
        )

        val initialState = LoginViewState.Initial
        val emailEnteredState = LoginViewState.Active(
            credentials = Credentials(email = Email(testEmail))
        )
        val emailPasswordEnteredState = LoginViewState.Active(
            credentials = completedCredentials,
        )
        val submittingState = LoginViewState.Submitting(
            credentials = completedCredentials,
        )
        val submissionErrorState = LoginViewState.SubmissionError(
            credentials = completedCredentials,
            errorMessage = UiText.ResourceText(R.string.unknown_error),
        )

        val expectedViewStates = listOf(
            initialState,
            emailEnteredState,
            emailPasswordEnteredState,
            submittingState,
            submissionErrorState,
        )

        testRobot
            .buildViewModel()
            .mockLoginForCredentials(
                credentials = completedCredentials,
                result = LoginResult.Failure.Unknown,
            )
            .expectViewStates(
                action = {
                    enterEmail(testEmail)
                    enterPassword(testPassword)
                    clickLoginButton()
                },
                viewStates = expectedViewStates,
            )
    }

    @Test
    fun testSubmitWithoutCredentials() = runBlockingTest {
        val credentials = Credentials()
        val initialState = LoginViewState.Initial
        val submittingState = LoginViewState.Submitting(
            credentials = credentials,
        )
        val invalidInputState = LoginViewState.Active(
            credentials = credentials,
            emailInputErrorMessage = UiText.ResourceText(R.string.err_empty_email),
            passwordInputErrorMessage = UiText.ResourceText(R.string.err_empty_password),
        )

        testRobot
            .buildViewModel()
            .mockLoginForCredentials(
                credentials = credentials,
                result = LoginResult.Failure.EmptyCredentials,
            )
            .expectViewStates(
                action = {
                    clickLoginButton()
                },
                viewStates = listOf(initialState, submittingState, invalidInputState),
            )
    }

}