package com.example.rparcas.ejemplojetpackcompose.domain.UseCase

import android.util.Log
import com.example.rparcas.ejemplojetpackcompose.data.database.dao.UserDao
import com.example.rparcas.ejemplojetpackcompose.data.database.entities.toDatabase
import com.example.rparcas.ejemplojetpackcompose.domain.model.User
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val userDao: UserDao) {


    suspend operator fun invoke(user: User){
        Log.d("PRUEBA-BORRAR",user.toDatabase().toString())
        userDao.deleteUser(user.toDatabase())
    }
}