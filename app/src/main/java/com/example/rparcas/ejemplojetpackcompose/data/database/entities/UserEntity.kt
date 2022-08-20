package com.example.rparcas.ejemplojetpackcompose.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rparcas.ejemplojetpackcompose.data.model.UserApi
import com.example.rparcas.ejemplojetpackcompose.domain.model.User
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Int?=0,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "lastName") val lastName:String,
    @ColumnInfo(name = "age") val age:String,
)

fun User.toDatabase() =  UserEntity(id = id, name = name, lastName = lastName, age = age)