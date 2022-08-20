package com.example.rparcas.ejemplojetpackcompose.presentation.EditUser

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rparcas.ejemplojetpackcompose.domain.UseCase.GetUserByIdUseCase
import com.example.rparcas.ejemplojetpackcompose.domain.UseCase.InsertUserUseCase
import com.example.rparcas.ejemplojetpackcompose.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val insertUserUseCase: InsertUserUseCase,
    savedStateHandle: SavedStateHandle // para recuperar los parametros pasados entre ventanas, en este caso el id del usuario
): ViewModel() {

    // control del estado de los text fields

    private val _userName = mutableStateOf(TextFieldState())
    val userName: State<TextFieldState> = _userName

    private val _userLastName = mutableStateOf(TextFieldState())
    val userLastName: State<TextFieldState> = _userLastName

    private val _userAge = mutableStateOf(TextFieldState())
    val userAge: State<TextFieldState> = _userAge

    private var currentUserId: Int? = null


    // con este tipo de variables podemos emitir ementos a la UI
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        // en el caso de abrir el screen y que por parametro se reciba un id
        // recuperar de la BD ese usuario y rellenar los text fields
        savedStateHandle.get<Int>("userId")?.let{ userId ->
            if(userId != -1){
                viewModelScope.launch {

                    // ?.also es cuando termine la operacion asincrona realiza la accion
                    // es equivalente al then de flutter y javascript
                    getUserByIdUseCase(userId)?.also{ user ->
                        currentUserId = user.id
                        _userName.value = userName.value.copy(
                            text = user.name
                        )
                        _userLastName.value = userLastName.value.copy(
                            text = user.lastName
                        )
                        _userAge.value = userAge.value.copy(
                            text = user.age
                        )
                    }
                }
            }
        }
    }



    fun onEvent(event:EditEvent){
        when(event){
            is EditEvent.EnterName -> {
                _userName.value = userName.value.copy(
                    text = event.value
                )
            }

            is EditEvent.EnterLastName -> {
                _userLastName.value = userLastName.value.copy(
                    text = event.value
                )
            }

            is EditEvent.EnterAge -> {

                _userAge.value = userAge.value.copy(
                    text = event.value
                )
            }

            // evento de añadir usuario o editarlo
            // en la BD esta puesto que cuando la id es igual se sobreecribe
            is EditEvent.InsertUser -> {
                viewModelScope.launch {
                    insertUserUseCase(
                        User(
                           name = userName.value.text,
                           lastName = userLastName.value.text,
                           age = userAge.value.text,
                           id = currentUserId
                        )
                    )

                    _eventFlow.emit(UiEvent.SaveUser)
                }
            }
        }
    }


    sealed class  UiEvent{
        object SaveUser:UiEvent()
    }

}