package com.example.rparcas.ejemplojetpackcompose.data

import android.util.Log
import com.example.rparcas.ejemplojetpackcompose.data.database.dao.UserDao
import com.example.rparcas.ejemplojetpackcompose.data.database.entities.UserEntity
import com.example.rparcas.ejemplojetpackcompose.data.database.entities.toDatabase
import com.example.rparcas.ejemplojetpackcompose.data.model.UserApi
import com.example.rparcas.ejemplojetpackcompose.data.network.UserApiClient
import com.example.rparcas.ejemplojetpackcompose.data.network.UserService
import com.example.rparcas.ejemplojetpackcompose.domain.model.User
import com.example.rparcas.ejemplojetpackcompose.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/// Creamos una interfaz para que nos sea mas facil
// mockearla y ayudarnos en los tests
interface UserRepository {

    suspend fun getNewUserFromApi(): User?

    suspend fun getUserByIdFromDB(id:Int): User?

    suspend fun getAllUsersFromDB():Flow<List<User>>

    suspend fun insertUser(user:UserEntity):Long

    suspend fun deleteUser(user: UserEntity)
}

class UserRespositoryImp @Inject constructor(
    private val api:UserService,
    private val userDao: UserDao)
    : UserRepository {

    override suspend fun getNewUserFromApi(): User? {
        val response: UserApi? = api.getUser()
        return response?.toDomain() // devolvera null en el caso de que response lo sea en otro caso devolvera el user.toDomain
    }

    override suspend fun getUserByIdFromDB(id:Int): User? {
        return userDao.getUserById(id)?.toDomain()
    }

    override suspend fun getAllUsersFromDB(): Flow<List<User>> {
       return userDao.getUsers().map { listUsers -> listUsers.map { it.toDomain() } }
    }

    override suspend fun insertUser(user: UserEntity):Long {

        val id:Long = userDao.insertUser(user)
        return id

    }

    override suspend fun deleteUser(user: UserEntity) {
        userDao.deleteUser(user)
    }



}


