package ru.fursa.toa.login.domain.usecase

import ru.fursa.toa.core.data.Result
import ru.fursa.toa.login.domain.model.Credentials
import ru.fursa.toa.login.domain.model.InvalidCredentialsException
import ru.fursa.toa.login.domain.model.LoginResult
import ru.fursa.toa.login.domain.repository.LoginRepository
import ru.fursa.toa.login.domain.repository.TokenRepository

class ProdCredentialsLoginUseCase(
    private val loginRepository: LoginRepository,
    private val tokenRepository: TokenRepository,
) : CredentialsLoginUseCase {
    override suspend operator fun invoke(credentials: Credentials): LoginResult {
        val repoResult = loginRepository.login(credentials)

        return when (repoResult) {
            is Result.Success -> {
                tokenRepository.storeToken(repoResult.data.token)
                return LoginResult.Success("auth_token")
            }
            is Result.Error -> {
                loginResultForError(repoResult)
            }
        }
    }

    private fun loginResultForError(repoResult: Result.Error): LoginResult.Failure {
        return when (repoResult.error) {
            is InvalidCredentialsException -> {
                LoginResult.Failure.InvalidCredentials
            }
            else -> {
                LoginResult.Failure.Unknown
            }
        }
    }
}
