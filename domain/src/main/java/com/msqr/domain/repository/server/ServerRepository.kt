package com.msqr.domain.repository.server

import com.msqr.domain.model.Response
import com.msqr.domain.model.Server
import kotlinx.coroutines.flow.Flow

interface ServerRepository {

    suspend fun addServer(server: Server) : Flow<Response<Boolean>>

    suspend fun findAllServers(): Flow<List<Server>>
}