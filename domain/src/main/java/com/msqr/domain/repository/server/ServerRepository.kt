package com.msqr.domain.repository.server

import kotlinx.coroutines.flow.Flow
import com.msqr.domain.model.Server

interface ServerRepository {

    suspend fun addServer(server: Server)

    suspend fun findAllServers(): Flow<List<Server>>
}