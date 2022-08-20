package com.example.rparcas.ejemplojetpackcompose.presentation.Users

import com.example.rparcas.ejemplojetpackcompose.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class UserState(
    val users: List<User> = emptyList(),
    val isLoading:Boolean = false
)