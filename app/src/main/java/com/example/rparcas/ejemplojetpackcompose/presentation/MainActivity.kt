package com.example.rparcas.ejemplojetpackcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.rparcas.ejemplojetpackcompose.ui.theme.EjemploJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            EjemploJetpackComposeTheme {
               Navigation() // con esto indicamos que inicie la app, por defecto
                // la navegacion arranca UsersScreen
            }
        }
    }
}

