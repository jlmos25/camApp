package com.msqr.data.datasources.remote.utils

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

inline fun <reified T> resultToFlow(context: Context, crossinline call: suspend () -> Response<T>):
        Flow<NetworkResponse<T>> {
    return flow {
        try {
            val c =call()
            if (c.isSuccessful){
                emit(NetworkResponse.Success(c.body()))
            }else{
                emit(NetworkResponse.Error(c.message()))
            }
        } catch (e:Exception){
            print(e)
            emit(NetworkResponse.Error("not connected"))
        }
    }.flowOn(Dispatchers.IO)
}