package com.example.rparcas.ejemplojetpackcompose.presentation.EditUser

sealed class EditEvent {

    data class EnterName(val value:String): EditEvent()
    data class EnterLastName(val value:String): EditEvent()
    data class EnterAge(val value:String): EditEvent()

    object InsertUser:EditEvent()
}