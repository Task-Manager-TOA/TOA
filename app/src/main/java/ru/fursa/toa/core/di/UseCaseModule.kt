package ru.fursa.toa.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.fursa.toa.login.domain.usecase.CredentialsLoginUseCase
import ru.fursa.toa.login.domain.usecase.ProdCredentialsLoginUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindCredentialsLoginUseCase(
        useCase: ProdCredentialsLoginUseCase
    ): CredentialsLoginUseCase
}