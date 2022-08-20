package com.example.rparcas.ejemplojetpackcompose.domain.UseCase

import com.example.rparcas.ejemplojetpackcompose.data.UserRespositoryImp
import com.example.rparcas.ejemplojetpackcompose.data.database.entities.toDatabase
import com.example.rparcas.ejemplojetpackcompose.domain.model.User
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val repository: UserRespositoryImp
) {

    suspend operator fun invoke(user: User){
        repository.insertUser(user.toDatabase())
    }

}