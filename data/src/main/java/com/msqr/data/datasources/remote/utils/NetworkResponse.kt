package com.msqr.data.datasources.remote.utils

sealed class NetworkResponse<T>() {

    data class Success<T>(val data: T?) : NetworkResponse<T>()
    data class Error<T>(val exception: String) : NetworkResponse<T>()
}

