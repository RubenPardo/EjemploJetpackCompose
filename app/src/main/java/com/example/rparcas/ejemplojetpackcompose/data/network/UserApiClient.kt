package com.example.rparcas.ejemplojetpackcompose.data.network
import com.example.rparcas.ejemplojetpackcompose.data.model.UserApi
import retrofit2.Response
import retrofit2.http.GET

interface UserApiClient {

    @GET("?name")
    suspend fun getUser(): Response<UserApi>

}