package com.bosta.bostaapp.core.network

// A generic class that contains data and status about loading this data.
sealed class Resource<T> {
    data class Progress<T>(val loading: Boolean, val partialData: T? = null) : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure<T>(val message: String?) : Resource<T>()

    companion object {
        fun <T> loading(isLoading: Boolean = true, partialData: T? = null): Resource<T> =
            Progress(isLoading, partialData)

        fun <T> success(data: T): Resource<T> = Success(data)

        fun <T> failure(e: String?): Resource<T> = Failure(e)
    }
}
