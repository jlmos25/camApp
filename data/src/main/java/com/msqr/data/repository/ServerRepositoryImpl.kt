package com.msqr.data.repository

import com.msqr.data.datasources.IServerLocalDataSource
import com.msqr.domain.model.Server
import com.msqr.domain.repository.server.ServerRepository
import kotlinx.coroutines.flow.Flow
import org.koin.java.KoinJavaComponent.inject

class ServerRepositoryImpl(): ServerRepository
{

    private val severLocalDataSource : IServerLocalDataSource by inject(IServerLocalDataSource::class.java)

    override suspend fun addServer(server: Server) {
        severLocalDataSource.addServer(server)
    }

    override suspend fun findAllServers(): Flow<List<Server>> {
        return severLocalDataSource.findAllServers()

    }


}