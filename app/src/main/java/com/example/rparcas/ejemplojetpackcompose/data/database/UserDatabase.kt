package com.example.rparcas.ejemplojetpackcompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rparcas.ejemplojetpackcompose.data.database.dao.UserDao
import com.example.rparcas.ejemplojetpackcompose.data.database.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase:RoomDatabase() {

    abstract fun getUserDao():UserDao

}