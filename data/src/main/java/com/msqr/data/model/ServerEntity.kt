package com.msqr.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Class that defines a server.
 */
@Entity(tableName = "servers")
open class ServerEntity {

    @PrimaryKey
    var serverIp:String= ""

    var serverName: String = ""

}