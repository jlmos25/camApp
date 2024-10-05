package com.msqr.domain.model

sealed class Response<T>(){
    class Sucess<T>(val data:T) : Response<T>()
    class Error<T>(val error:T) : Response<T>()

}
