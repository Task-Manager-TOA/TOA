package ru.fursa.toa.login.domain.usecase

interface LoginUseCase {
    suspend fun login(email: String, password: String)
}