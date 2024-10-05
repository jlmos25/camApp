package com.msqr.data.di

import androidx.room.Room
import com.msqr.data.datasources.IServerLocalDataSource
import com.msqr.data.datasources.ServerLocalDataSource
import com.msqr.data.db.AppDatabase
import com.msqr.data.model.ServerEntity
import com.msqr.data.repository.ServerRepositoryImpl
import com.msqr.domain.model.Server
import com.msqr.domain.repository.server.ServerRepository
import dev.krud.shapeshift.ShapeShiftBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataBaseModule = module {
    single {
       Room.databaseBuilder(androidContext(),AppDatabase::class.java,"appDatabase").build()
    }

    single { get<AppDatabase>().userDao()  }


    singleOf(::ServerLocalDataSource) bind IServerLocalDataSource::class
    singleOf(::ServerRepositoryImpl) bind ServerRepository::class

    single { ShapeShiftBuilder()
        .withMapping<ServerEntity, Server> {
            ServerEntity::serverIp mappedTo Server::serverIp
            ServerEntity::serverName mappedTo Server::serverName
        }.build() }

}

