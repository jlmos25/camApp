package com.msqr.data.datasources

import com.msqr.data.db.dao.ServerDao
import com.msqr.data.model.ServerEntity
import com.msqr.domain.model.Server
import dev.krud.shapeshift.ShapeShift
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.java.KoinJavaComponent.inject

class ServerLocalDataSource(private val serverDao: ServerDao): IServerLocalDataSource {

    private val shapeShift : ShapeShift by inject(ShapeShift::class.java)
    override suspend fun addServer(server: Server) {
        serverDao.insert(ServerEntity().apply {
            serverName = server.serverName
            serverIp = server.serverIp
        })
    }

    override suspend fun findAllServers(): Flow<List<Server>> {
        return serverDao.findAll().map{ serverEntities ->
            shapeShift.mapCollection(serverEntities)
        }
    }


}