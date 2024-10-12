package com.msqr.data.datasources.remote

import com.msqr.data.datasources.api.model.Health
import com.msqr.data.datasources.remote.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface ICamApiRemoteDatasource {

    fun serverStatus(ip:String): Flow<NetworkResponse<Health>>
}