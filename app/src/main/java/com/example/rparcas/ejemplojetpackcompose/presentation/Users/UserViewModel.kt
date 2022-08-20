package com.example.rparcas.ejemplojetpackcompose.presentation.Users

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rparcas.ejemplojetpackcompose.domain.UseCase.DeleteUserUseCase
import com.example.rparcas.ejemplojetpackcompose.domain.UseCase.GetAllUsersUseCase
import com.example.rparcas.ejemplojetpackcompose.domain.UseCase.GetNewUserUseCase
import com.example.rparcas.ejemplojetpackcompose.domain.model.User
import com.example.rparcas.ejemplojetpackcompose.presentation.EditUser.EditViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getNewUserUseCase: GetNewUserUseCase,
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase

) :ViewModel() {


    private val _state = mutableStateOf(UserState())
    val state: State<UserState> = _state


    init{

        // para poder hacer que la lista de usuarios se actualice de forma dinamica se
        // debe devolver una FlowList y hacer lo siguiente
        getAllUsersUseCase().onEach { users ->

            _state.value = state.value.copy(
                users = users,
                isLoading = false
            )

        }.launchIn(viewModelScope)



    }

    fun onEvent(event: UserEvent){
        when(event){
            is UserEvent.DeleteUser -> {
                viewModelScope.launch {
                    isLoading(true)
                    deleteUserUseCase(event.user)
                    isLoading(false)
                }
            }

            is UserEvent.AddRandomUser -> {
                viewModelScope.launch {
                    isLoading(true)
                    getNewUserUseCase()
                    isLoading(false)
                }
            }
        }
    }

    fun isLoading(value:Boolean){
        _state.value = state.value.copy(
            isLoading = value
        )
    }




}