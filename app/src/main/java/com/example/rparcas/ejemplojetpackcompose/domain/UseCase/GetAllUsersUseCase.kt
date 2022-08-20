package com.example.rparcas.ejemplojetpackcompose.domain.UseCase

import android.util.Log
import com.example.rparcas.ejemplojetpackcompose.data.database.dao.UserDao
import com.example.rparcas.ejemplojetpackcompose.data.database.entities.UserEntity
import com.example.rparcas.ejemplojetpackcompose.domain.model.User
import com.example.rparcas.ejemplojetpackcompose.domain.model.toDomain
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(private val userDao: UserDao) {


    operator fun invoke(): Flow<List<User>> {
        return userDao.getUsers().map  { listUsers -> listUsers.map { userEntity ->  userEntity.toDomain() } }
    }

}