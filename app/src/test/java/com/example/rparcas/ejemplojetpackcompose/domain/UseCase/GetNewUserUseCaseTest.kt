package com.example.rparcas.ejemplojetpackcompose.domain.UseCase

import com.example.rparcas.ejemplojetpackcompose.data.UserRepository
import com.example.rparcas.ejemplojetpackcompose.data.UserRespositoryImp
import com.example.rparcas.ejemplojetpackcompose.data.database.entities.toDatabase
import com.example.rparcas.ejemplojetpackcompose.domain.model.User
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNewUserUseCaseTest{

    @RelaxedMockK
    private lateinit var userRepository: UserRespositoryImp

    lateinit var getNewUserUseCase: GetNewUserUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getNewUserUseCase = GetNewUserUseCase(userRepository)
    }


    // el run blocking es para tratar con las corrutinas
    @Test
    fun whenTheApiDoesntReturnAnythingThenGetNullUser() = runBlocking {
        // La estructura de un test es:
        // Given When Then, dada una condicon//mock, cuando se llama a una funcion,
        // entonces comprueba

        // Given
        coEvery { userRepository.getNewUserFromApi() } returns null

        // When
        val respuesta = getNewUserUseCase()

        // Then
        assert(respuesta == null)
    }

    @Test
    fun whenTheApiReturnAUserSaveItOnDBAndReturnIt() = runBlocking {

        // Given
        val respuestaDeApi:User = User(null,"nombre","apellido","1234")
        val idAlInsertarEnDb:Long = 30
        val respuestaDeCasoUso:User = User(idAlInsertarEnDb.toInt(),"nombre","apellido","1234")

        // respuesta de la api
        coEvery { userRepository.getNewUserFromApi() } returns respuestaDeApi
        // cuando insertemos el usuario en la DB devuelve su id el cual se le pondra luego
        coEvery { userRepository.insertUser(any()) } returns idAlInsertarEnDb

        // When
        val respuesta = getNewUserUseCase()

        // Then
        coVerify(exactly = 1){ userRepository.insertUser(respuestaDeApi.toDatabase())}
        assert(respuesta == respuestaDeCasoUso)
    }

}