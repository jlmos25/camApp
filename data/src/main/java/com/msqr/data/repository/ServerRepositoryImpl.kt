package com.msqr.data.repository

import com.msqr.data.datasources.IServerLocalDataSource
import com.msqr.domain.model.Response
import com.msqr.domain.model.Server
import com.msqr.domain.repository.server.ServerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import org.koin.java.KoinJavaComponent.inject

class ServerRepositoryImpl(): ServerRepository
{

    private val severLocalDataSource : IServerLocalDataSource by inject(IServerLocalDataSource::class.java)

    override suspend fun addServer(server: Server): Flow<Response<Boolean>> {
        return severLocalDataSource.exists(server.serverIp).take(1).map {exists ->
            if (exists){
               return@map Response.Sucess(false)
            }
            severLocalDataSource.addServer(server)
            return@map Response.Sucess(true)
        }

    }

    override suspend fun findAllServers(): Flow<List<Server>> {
        return severLocalDataSource.findAllServers()

    }


}