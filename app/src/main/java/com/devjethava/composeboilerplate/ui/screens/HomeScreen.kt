package com.devjethava.composeboilerplate.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.devjethava.composeboilerplate.BoilerplateApplication
import com.devjethava.composeboilerplate.R
import com.devjethava.composeboilerplate.database.entity.UserEntity
import com.devjethava.composeboilerplate.ui.MainActivity
import com.devjethava.composeboilerplate.ui.navigation.ComposeBoilerplateNavHost
import com.devjethava.composeboilerplate.ui.navigation.Screen
import com.devjethava.composeboilerplate.ui.theme.ComposeBoilerplateTheme
import com.devjethava.composeboilerplate.utils.Constants
import com.devjethava.composeboilerplate.utils.convertDateFormat
import com.devjethava.composeboilerplate.viewmodel.MainViewModel
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: MainViewModel = hiltViewModel()
    val userResponse = viewModel.userResponse.collectAsState().value
    val isShowNextScreenButton by remember {
        mutableStateOf(true)
    }

    var userData by remember {
        mutableStateOf(UserEntity())
    }

    LaunchedEffect(key1 = userResponse) {
        userResponse.userResponse?.let { response ->
            val data = response.results.first()
            Log.e("MainActivity", Gson().toJson(data))
            userData = viewModel.addUserToDatabase(data)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /**
             * For Display Image on top
             */
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(userData.imageURL.ifEmpty { Constants.PLACEHOLDER_IMAGE_URL })
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_user_placeholder),
                    contentDescription = stringResource(R.string.app_name),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(170.dp)
                )
            }

            /**
             * For display Name Field
             */
            OutlinedTextField(
                value = userData.name,
                onValueChange = {
                    userData.name = it
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.lbl_name),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.lbl_name),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyMedium,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onDone = {

                    }
                ),
                readOnly = true,
                singleLine = true,
                isError = userResponse.error.isNotEmpty(),
                supportingText = {
                    if (userResponse.error.isNotEmpty()) TextFieldError(textError = userResponse.error)
                },
            )

            /**
             * For display E-mail Field
             */
            OutlinedTextField(
                value = userData.email,
                onValueChange = {
                    userData.email = it
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.lbl_email),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.lbl_email),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyMedium,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ),
                keyboardActions = KeyboardActions(
                    onDone = {

                    }
                ),
                readOnly = true,
                singleLine = true,
                isError = userResponse.error.isNotEmpty(),
                supportingText = {
                    if (userResponse.error.isNotEmpty()) TextFieldError(textError = userResponse.error)
                },
            )

            /**
             * For display Phone Number Field
             */
            OutlinedTextField(
                value = userData.phoneNumber,
                onValueChange = {
                    userData.phoneNumber = it
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.lbl_phone_number),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.lbl_phone_number),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyMedium,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Phone
                ),
                keyboardActions = KeyboardActions(
                    onDone = {

                    }
                ),
                readOnly = true,
                singleLine = true,
                isError = userResponse.error.isNotEmpty(),
                supportingText = {
                    if (userResponse.error.isNotEmpty()) TextFieldError(textError = userResponse.error)
                },
            )

            /**
             * For display Gender Field
             */
            OutlinedTextField(
                value = userData.gender.capitalize(Locale.ENGLISH),
                onValueChange = {
                    userData.gender = it
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.lbl_gender),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.lbl_gender),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyMedium,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onDone = {

                    }
                ),
                readOnly = true,
                singleLine = true,
                isError = userResponse.error.isNotEmpty(),
                supportingText = {
                    if (userResponse.error.isNotEmpty()) TextFieldError(textError = userResponse.error)
                },
            )

            /**
             * For display Date of Birth Field
             */
            OutlinedTextField(
                value = userData.dob.convertDateFormat(),
                onValueChange = {
                    userData.dob = it
                },
                placeholder = {
                    Text(
                        text = Constants.ddMMyyyyHHmm,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.lbl_dob),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyMedium,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onDone = {

                    }
                ),
                readOnly = true,
                singleLine = true,
                isError = userResponse.error.isNotEmpty(),
                supportingText = {
                    if (userResponse.error.isNotEmpty()) TextFieldError(textError = userResponse.error)
                },
            )

            /**
             * For Display Bottom Button
             */
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (userResponse.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Button(modifier = Modifier.padding(8.dp), onClick = {
                        viewModel.getUserFromApi()
                    }) {
                        Text(text = "Click Me")
                    }
                    if (isShowNextScreenButton)
                        Button(modifier = Modifier.padding(8.dp), onClick = {
                            navController.navigate(Screen.MainScreen.route)
                        }) {
                            Text(text = "Move to Next")
                        }
                }
            }
        }
    }
}

/**
 * To be removed when [TextField]s support error
 */
@Composable
fun TextFieldError(textError: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = textError,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.error
        )
    }
}