package com.example.rparcas.ejemplojetpackcompose.presentation.Users

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.rparcas.ejemplojetpackcompose.R
import com.example.rparcas.ejemplojetpackcompose.domain.model.User
import com.example.rparcas.ejemplojetpackcompose.presentation.Screen
import com.example.rparcas.ejemplojetpackcompose.presentation.Users.componentes.UserItem
import kotlinx.coroutines.flow.collectLatest


@Composable
fun UsersScreen(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel()
){
    val state = viewModel.state.value


    Scaffold(
        topBar = {
            UsersTopBar { viewModel.onEvent(UserEvent.AddRandomUser) }
        },
        floatingActionButton = {
            UsersFab(
                onFabClicked = {navController.navigate(Screen.Edit.route)} // al no pasrle la id creare la ventna vacia para crear uno nuevo
            )
        },
        content = { innerPadding ->
            if(state.isLoading){
                LoadingScreen()
            }
            else{

                UsersContent(
                    modifier = Modifier.padding(innerPadding),
                    onDeleteUser = {viewModel.onEvent(UserEvent.DeleteUser(it))},
                    onEditUser = {
                        navController.navigate(
                            route = Screen.Edit.passId(it)
                        )
                    },
                    users = state.users
                )

            }
        }
    )
}

@Composable
fun LoadingScreen() {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Loading")
            CircularProgressIndicator()
        }
    }
}

@Composable
fun UsersContent(
    modifier: Modifier = Modifier,
    onDeleteUser: (user:User) -> Unit,
    onEditUser: (id:Int) -> Unit,
    users: List<User> = emptyList()
) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = modifier
    ) {
        if(users.isEmpty()){
            // sin usuarios
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "No hay usuarios creados")
                }
            }
        }else{
            // con usuarios
            LazyColumn{
                items(users){ user ->
                    UserItem(
                        user = user,
                        onEditUser = { user.id?.let { onEditUser(it) } },// no va a poder ser nulo aqui, pero me obliga a ponerlo por si acaso
                        onDeleteUser = {onDeleteUser(user)}
                    )
                }
            }
        }

    }
}


@Composable
fun UsersTopBar(
    modifier: Modifier = Modifier,
    onAddRandomUser: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.users),
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            )
                },
        backgroundColor = MaterialTheme.colors.surface,
        actions = {
            IconButton(onClick = onAddRandomUser) {
                Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(id = R.string.add_random_user))
            }
        }
    )

}


@Composable
fun UsersFab(
    modifier: Modifier = Modifier,
    onFabClicked: () -> Unit) {

  FloatingActionButton(

        onClick = onFabClicked,
        modifier = modifier
            .height(52.dp)
            .widthIn(min = 52.dp),
        backgroundColor = MaterialTheme.colors.primary

    ) {
        Icon(imageVector = Icons.Outlined.Add, contentDescription = stringResource(id = R.string.add_user))
    }
}

