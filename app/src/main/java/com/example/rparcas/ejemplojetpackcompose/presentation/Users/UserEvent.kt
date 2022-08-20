package com.example.rparcas.ejemplojetpackcompose.presentation.Users

import com.example.rparcas.ejemplojetpackcompose.domain.model.User

sealed class UserEvent{
    data class DeleteUser(val user: User):UserEvent()
    object AddRandomUser:UserEvent()
}
