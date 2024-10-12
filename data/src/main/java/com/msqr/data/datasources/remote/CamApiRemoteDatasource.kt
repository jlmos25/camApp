package com.msqr.data.datasources.remote

import android.content.Context
import com.msqr.data.datasources.api.CamApiService
import com.msqr.data.datasources.api.model.Health
import com.msqr.data.datasources.remote.utils.BaseUrlInterceptor
import com.msqr.data.datasources.remote.utils.NetworkResponse
import com.msqr.data.datasources.remote.utils.resultToFlow
import kotlinx.coroutines.flow.Flow
import org.koin.java.KoinJavaComponent

class CamApiRemoteDatasource(private val context: Context): ICamApiRemoteDatasource {

    private val camApiService : CamApiService by KoinJavaComponent.inject(
        CamApiService::class.java
    )

    private val baseUrlInterceptor: BaseUrlInterceptor by KoinJavaComponent.inject(
        BaseUrlInterceptor::class.java
    )


    override fun serverStatus(ip:String): Flow<NetworkResponse<Health>> {
        baseUrlInterceptor.updateBaseUrl(ip)
        return resultToFlow(context){
            camApiService.isAlive()
        }
    }
}