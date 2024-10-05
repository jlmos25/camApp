package com.msqr.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.msqr.data.model.ServerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ServerDao {

    @Insert
    suspend fun insert(serverEntity: ServerEntity)

    @Query("select * from servers s order by s.serverName asc")
    fun findAll(): Flow<List<ServerEntity>>

}