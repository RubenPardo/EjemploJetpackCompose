package com.example.rparcas.ejemplojetpackcompose.data.network

import android.util.Log
import com.example.rparcas.ejemplojetpackcompose.data.model.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

///
/// Esta clase es necesaria en adicion a UserApiClient para poder inyectarla en el repositorio
class UserService @Inject constructor(private val apiClient: UserApiClient){


    suspend fun getUser():UserApi?{
        return withContext(Dispatchers.IO){
            val response: Response<UserApi> = apiClient.getUser()
            response.body()
        }
    }

}