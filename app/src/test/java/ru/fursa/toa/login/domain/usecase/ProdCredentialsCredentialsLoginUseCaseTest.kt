package ru.fursa.toa.login.domain.usecase

import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import ru.fursa.toa.core.data.Result
import ru.fursa.toa.fakes.FakeLoginRepository
import ru.fursa.toa.fakes.FakeTokenRepository
import ru.fursa.toa.login.domain.model.AuthToken
import ru.fursa.toa.login.domain.model.Credentials
import ru.fursa.toa.login.domain.model.Email
import ru.fursa.toa.login.domain.model.InvalidCredentialsException
import ru.fursa.toa.login.domain.model.LoginResponse
import ru.fursa.toa.login.domain.model.LoginResult
import ru.fursa.toa.login.domain.model.Password
import ru.fursa.toa.login.domain.model.RefreshToken
import ru.fursa.toa.login.domain.model.Token

class ProdCredentialsCredentialsLoginUseCaseTest {
    private val defaultCredentials = Credentials(
        email = Email("ifv93@test.com"),
        password = Password("123456"),
    )

    private val defaultToken = Token(
        authToken = AuthToken("auth_token"),
        refreshToken = RefreshToken("refresh_token"),
    )

    private lateinit var loginRepository: FakeLoginRepository
    private lateinit var tokenRepository: FakeTokenRepository

    @Before
    fun setUp() {
        loginRepository = FakeLoginRepository()
        tokenRepository = FakeTokenRepository()
    }

    @Test
    fun testSuccessfulLogin() = runBlockingTest {
        val loginResponse = Result.Success(
            LoginResponse(
                token = defaultToken,
            )
        )

        loginRepository.mockLoginWithCredentials(
            defaultCredentials,
            loginResponse,
        )

        val prodUseCase = ProdCredentialsLoginUseCase(
            loginRepository = loginRepository.mock,
            tokenRepository = tokenRepository.mock,
        )

        val result = prodUseCase(defaultCredentials)
        println(result)
        assert(result == LoginResult.Success("auth_token"))
        tokenRepository.verifyTokenStored(defaultToken)
    }

    @Test
    fun testUnknownFailureLogin() = runBlockingTest {
        val loginResponse: Result<LoginResponse> = Result.Error(
            error = Throwable("")
        )

        loginRepository.mockLoginWithCredentials(
            defaultCredentials,
            loginResponse,
        )

        val prodUseCase = ProdCredentialsLoginUseCase(
            loginRepository = loginRepository.mock,
            tokenRepository = tokenRepository.mock,
        )

        val result = prodUseCase(defaultCredentials)
        assert(result == LoginResult.Failure.Unknown)
        tokenRepository.verifyNoTokenStored()
    }

    @Test
    fun testInvalidCredentialLogin() = runBlockingTest {
        val loginResponse: Result<LoginResponse> = Result.Error(
            InvalidCredentialsException()
        )

        loginRepository.mockLoginWithCredentials(
            defaultCredentials,
            loginResponse,
        )

        val useCase = ProdCredentialsLoginUseCase(
            loginRepository = loginRepository.mock,
            tokenRepository = tokenRepository.mock,
        )

        val result = useCase(defaultCredentials)
        assert(result == LoginResult.Failure.InvalidCredentials)
        tokenRepository.verifyNoTokenStored()
    }
}
