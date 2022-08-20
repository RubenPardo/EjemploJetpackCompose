package com.example.rparcas.ejemplojetpackcompose.data.database.dao

import androidx.room.*
import com.example.rparcas.ejemplojetpackcompose.data.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao{

    @Query("SELECT * FROM user_table")
    fun getUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user_table where id = :id")
    suspend fun getUserById(id:Int):UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:UserEntity):Long

    @Delete
    suspend fun deleteUser(user:UserEntity?)

}