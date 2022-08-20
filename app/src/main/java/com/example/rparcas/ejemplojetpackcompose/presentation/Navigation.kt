package com.example.rparcas.ejemplojetpackcompose.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rparcas.ejemplojetpackcompose.presentation.EditUser.EditScreen
import com.example.rparcas.ejemplojetpackcompose.presentation.Users.UsersScreen

/**
 * Composable para definir el controlador de navegacion
 * lo iniciamos con un NavHost con un destino inicial que es nuestra clase
 * Users
 */
@Composable
fun Navigation (){

    val navController = rememberNavController()
    
    NavHost(navController = navController,
            startDestination = Screen.Users.route // ventana inicial
    ){
        // Las diferentes rutas que se pueden cargar

        // Ruta de users
        composable(route= Screen.Users.route){
            UsersScreen(navController=navController)
        }

        // ruta de edit user con parametros
        composable(
            route = Screen.Edit.route, // 1. ruta
            arguments = listOf( // 2. argumentos
                navArgument(
                    name = "userId" // paremtro id
                ){
                    type = NavType.IntType // tipo del parametro
                    defaultValue = -1
                }
            )
        ){
            EditScreen(navController = navController)// 3. Composable
        }
    }
}