package com.example.rparcas.ejemplojetpackcompose.presentation.EditUser

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rparcas.ejemplojetpackcompose.R
import com.example.rparcas.ejemplojetpackcompose.presentation.EditUser.components.UserInputText
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditScreen(
    navController: NavController,
    viewModel: EditViewModel = hiltViewModel()
){

    val nameState = viewModel.userName.value
    val lastNameState = viewModel.userLastName.value
    val ageState = viewModel.userAge.value


    /// captar eventos del view model
    // en este caso captamos cuando se guarda un usuario
    LaunchedEffect(key1 = true, ){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is EditViewModel.UiEvent.SaveUser ->{
                    navController.navigateUp()
                }
            }
        }
    }
    
    Scaffold(
        
        topBar = {
            EditTopBar(
                topAppBarText = stringResource(id = R.string.add_user)
            )
        },// top bar
        content = {
            EditContent(
                name = nameState.text,
                lastName = lastNameState.text,
                age = ageState.text,
                onEvent = {viewModel.onEvent(it)}

            )
        },// content
        
        bottomBar =  {
            EditBottomBar(
                onInsertUser = {
                    viewModel.onEvent(EditEvent.InsertUser)
                }
            )
        }

    )// Scaffold


}

@Composable
fun EditBottomBar(
    modifier: Modifier = Modifier,
    onInsertUser: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 14.dp),
        onClick = {onInsertUser()}
    ) {
        Text(text = stringResource(id = R.string.add_user))
    }
}

@Composable
fun EditContent(
    name: String,
    lastName: String,
    age: String,
    onEvent: (EditEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer( modifier =  Modifier.height(44.dp))

        UserInputText(
            text = name,
            hint = stringResource(id = R.string.name),
            onTextChange = {onEvent(EditEvent.EnterName(it))}
        )

        UserInputText(
            text = lastName,
            hint = stringResource(id = R.string.last_name),
            onTextChange = {onEvent(EditEvent.EnterLastName(it))}
        )

        UserInputText(
            text = age,
            hint = stringResource(id = R.string.age),
            onTextChange = {onEvent(EditEvent.EnterAge(it))}
        )
    }
}

@Composable
fun EditTopBar(topAppBarText: String) {
    TopAppBar(
        title = {
            Text(
                text = topAppBarText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            )// text
        },// title
        backgroundColor = MaterialTheme.colors.surface
    )
}
