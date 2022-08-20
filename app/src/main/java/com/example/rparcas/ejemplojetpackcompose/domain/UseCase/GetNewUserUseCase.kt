package com.example.rparcas.ejemplojetpackcompose.domain.UseCase

import android.util.Log
import com.example.rparcas.ejemplojetpackcompose.data.UserRespositoryImp
import com.example.rparcas.ejemplojetpackcompose.data.database.entities.toDatabase
import com.example.rparcas.ejemplojetpackcompose.domain.model.User
import javax.inject.Inject

class GetNewUserUseCase  @Inject constructor(private val repository:UserRespositoryImp){


    suspend operator fun invoke(): User?{
        val userApi = repository.getNewUserFromApi()

        return if(userApi != null){
            val id = repository.insertUser(userApi.toDatabase())
            Log.d("PRUEBA", userApi.toDatabase().toString())
            userApi.id = id.toInt()
            Log.d("PRUEBA", userApi.toDatabase().toString())
            // return
            userApi
        }else{
            // return
            null
        }

    }

}