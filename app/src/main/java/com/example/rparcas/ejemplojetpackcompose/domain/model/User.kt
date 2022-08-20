package com.example.rparcas.ejemplojetpackcompose.domain.model

import com.example.rparcas.ejemplojetpackcompose.data.database.entities.UserEntity
import com.example.rparcas.ejemplojetpackcompose.data.model.UserApi

data class User(
    var id:Int?,
    val name:String,
    val lastName:String,
    val age:String)


fun UserEntity.toDomain() = User(id = id, name = name, lastName = lastName, age = age)
fun UserApi.toDomain() = User(id=null,name = results[0].userName.name, lastName = results[0].userName.lastName,age=results[0].userDob.age)