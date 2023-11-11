package ru.fursa.toa.core.data

sealed class DataResponse<out T> {
    data class Success<out T>(val data: T): DataResponse<T>()
    data class Error<out T>(val error: Throwable): DataResponse<T>()
}
