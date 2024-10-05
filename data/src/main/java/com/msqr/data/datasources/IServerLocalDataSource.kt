package com.msqr.data.datasources

import com.msqr.domain.model.Server
import kotlinx.coroutines.flow.Flow

interface IServerLocalDataSource {

    suspend fun addServer(server: Server)

    suspend fun findAllServers(): Flow<List<Server>>

    suspend fun exists(serverIp: String): Flow<Boolean>
}