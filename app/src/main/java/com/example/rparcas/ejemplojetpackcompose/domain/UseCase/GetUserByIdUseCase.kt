package com.example.rparcas.ejemplojetpackcompose.domain.UseCase

import com.example.rparcas.ejemplojetpackcompose.data.UserRespositoryImp
import com.example.rparcas.ejemplojetpackcompose.domain.model.User
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val repository: UserRespositoryImp
) {

    suspend operator fun invoke(id:Int): User?{
        return repository.getUserByIdFromDB(id)
    }

}