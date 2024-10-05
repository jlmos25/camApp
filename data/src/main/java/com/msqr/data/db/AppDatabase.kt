package com.msqr.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.msqr.data.db.dao.ServerDao
import com.msqr.data.model.ServerEntity

@Database(entities = [ServerEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): ServerDao
}