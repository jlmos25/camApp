package com.msqr.data.repository

import com.msqr.data.datasources.local.IServerLocalDataSource
import com.msqr.data.datasources.remote.ICamApiRemoteDatasource
import com.msqr.data.datasources.remote.utils.NetworkResponse
import com.msqr.domain.model.Response
import com.msqr.domain.model.Server
import com.msqr.domain.repository.server.ServerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import org.koin.java.KoinJavaComponent.inject

class ServerRepositoryImpl: ServerRepository
{
    private val severLocalDataSource : IServerLocalDataSource by inject(IServerLocalDataSource::class.java)
    private val camApiRemoteDatasource : ICamApiRemoteDatasource by inject(ICamApiRemoteDatasource::class.java)

    override suspend fun addServer(server: Server): Flow<Response<Boolean>> {
        return severLocalDataSource.exists(server.serverIp).take(1).map {exists ->
            if (exists){
               return@map Response.Success(false)
            }
            severLocalDataSource.addServer(server)
            return@map Response.Success(true)
        }
    }

    override suspend fun checkServerConnection(server: Server) :Flow<Response<Boolean>>{
       return camApiRemoteDatasource.serverStatus(server.serverIp).map {response ->
            when(response) {
                is NetworkResponse.Success -> return@map Response.Success(response.data!!.status ==
                        "UP")
                is NetworkResponse.Error -> return@map Response.Error(response.exception)
            }
        }
    }

    override suspend fun findAllServers(): Flow<List<Server>> {
        return severLocalDataSource.findAllServers()
    }


}