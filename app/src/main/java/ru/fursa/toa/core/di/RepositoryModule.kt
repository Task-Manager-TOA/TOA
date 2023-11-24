package ru.fursa.toa.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.fursa.toa.login.domain.repository.DemoLoginService
import ru.fursa.toa.login.domain.repository.DemoTokenService
import ru.fursa.toa.login.domain.repository.LoginRepository
import ru.fursa.toa.login.domain.repository.TokenRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTokenRepository(
        tokenRepository: DemoTokenService
    ): TokenRepository

    @Binds
    abstract fun bindLoginRepository(
        loginRepository: DemoLoginService
    ): LoginRepository
}