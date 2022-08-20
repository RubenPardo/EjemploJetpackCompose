package com.example.rparcas.ejemplojetpackcompose.presentation

/**
 * Clase Sealed (enum de alto nivel)
 * para definir las diferentes rutas de nuestras ventanas
 */
sealed class Screen(val route: String){
    object Users: Screen("users")
    object Edit: Screen("edit?userId={userId}"){
        fun passId(userId:Int): String{
            return "edit?userId=$userId"
        }
    }
}