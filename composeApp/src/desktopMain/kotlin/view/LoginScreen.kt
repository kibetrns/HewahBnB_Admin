package view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import viewmodel.HBAdminViewModel
import util.Result
import util.isEmailValid
import util.isPasswordValid
import view.components.MessageCont
import view.components.MessageType

@Composable
fun LoginScreen(
    hBAdminViewModel: HBAdminViewModel,
    navigateToHomeScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit
) {

    var passwordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }


    val password by  hBAdminViewModel.password.collectAsState()
    val email by hBAdminViewModel.email.collectAsState()

    val errorMessage by hBAdminViewModel.errorMessage.collectAsState()

    val scope = rememberCoroutineScope()
    val loginUser by rememberUpdatedState(hBAdminViewModel.loggedInUser.collectAsState().value)

    var isDialogVisible by remember { mutableStateOf(false) }

    var isPasswordInvalid by remember { mutableStateOf(false) }

    var isEmailValid by remember { mutableStateOf(true) }

    var isSubmitAttempted by remember { mutableStateOf(false) }

    if (isSubmitAttempted) {
        AlertDialog(
            onDismissRequest = {
                isSubmitAttempted = false

            },
            title = {
                Text(
                    text = "Email Validation Error",
                )
            },
            confirmButton = {
                Button(onClick = {
                    isSubmitAttempted = false
                }) {
                    Text("OK")
                }
            },
            text = {
                MessageCont(
                    messageType = MessageType.Error,
                    message = "Please enter a valid email address."
                )
            }
        )
    }

    var isFieldsEmpty by remember { mutableStateOf(false) }

    LaunchedEffect(loginUser) {
        if (loginUser is Result.Error) {
            isDialogVisible = true
        }
    }


    if (isFieldsEmpty) {
        AlertDialog(
            onDismissRequest = {
                isFieldsEmpty = false
            },
            title = {
                Text(
                    text = "Empty Field Error",
                )
            },
            confirmButton = {
                Button(onClick = {
                    isFieldsEmpty = false
                }) {
                    Text("OK")
                }
            },
            text = {
                MessageCont(
                    messageType = MessageType.Error,
                    message = "Please fill in all the required fields."
                )
            }
        )
    }

    when (loginUser) {
        is Result.Error -> {
            if (isDialogVisible) {

                AlertDialog(
                    onDismissRequest = {
                        isDialogVisible = false
                        isLoading = false
                    },
                    title = {

                        if ((loginUser as Result.Error).statusCode == null) {
                            Text(
                                text = "Error: ${(loginUser as Result.Error).message}"
                            )
                        } else {
                            Text(
                                text = "Error code ${(loginUser as Result.Error).statusCode}"
                            )
                        }
                    },
                    confirmButton = {
                        Button(onClick = {
                            isDialogVisible = false
                            isLoading = false
                        }) {
                            Text("OK")
                        }
                    },
                    text = {
                        MessageCont(
                            messageType = MessageType.Error,
                            message = "${(loginUser as Result.Error).message}"
                        )
                    })
            }

        }
        is Result.Success -> {
            AlertDialog(
                onDismissRequest = {
                    isDialogVisible = false
                    isLoading = false
                },
                title = {
                    Text(
                        text = "Success"
                    )
                },
                confirmButton = {
                    Button(onClick = {
                        isDialogVisible = false
                        isLoading = false
                    }) {
                        Text("Continue")
                    }
                },
                text = {
                    MessageCont(
                        messageType = MessageType.Success,
                        message = "Login Successfully"
                    )
                })

            navigateToHomeScreen()
        }
        is Result.Loading -> {

        }
        else -> {

        }
    }

    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        item() {
            Image(
                painter = painterResource(resourcePath = "hewabnb_logo.png"),
                contentDescription = "HewahBnBlogo",
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineSmall.copy(
                    MaterialTheme.colorScheme.primary
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
        }


        item {

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { hBAdminViewModel.email.value = it},
                    label = { Text(text ="Enter email address") },
                    singleLine = true,
                    placeholder = { Text(text ="Email Address") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                )

                Spacer(modifier = Modifier.height(8.dp))


                Text(
                    text = "Password",
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { hBAdminViewModel.password.value = it },
                    label = { Text(text = "Enter Password") },
                    singleLine = true,
                    placeholder = { Text(text = "Password") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        val description =
                            if (passwordVisible) "Hide password" else "Show password"

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, description)
                        }
                    },
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(0.35f)
                ) {
                    Text(text = "")
                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "Forgot Password",
                        modifier = Modifier.clickable {

                        },
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = LocalContentColor.current
                        ),
                        textDecoration = TextDecoration.Underline
                    )
                }

            }
        }

        item {

            Button(
                modifier = Modifier
                    .padding(top = 16.dp),
                onClick = {
                    /*TODO(
                       1.) Fix the button being clicked more than once for navigation to work.
                       2) Fix the toast not showing when the result is success and when
                       there is an error
                       3.) Handle input fields validations
                     */

                    isSubmitAttempted = true

                    if (email.isEmpty() || password.isEmpty()) {
                        isFieldsEmpty = true
                        return@Button
                    }


                    if (!isEmailValid(email = email)) {
                        return@Button
                    }

                    isLoading = true
                    isSubmitAttempted = false





                    scope.launch {

                        val loginUpResult = async {
                            hBAdminViewModel.loginUser()
                        }

                        loginUpResult.await()
                    }

                    when (loginUser) {
                        is Result.Success -> {
                            isLoading = false
                            navigateToHomeScreen()
                        }

                        is Result.Loading -> {
                            isLoading = true
                        }

                        is Result.Error -> {
                            isLoading = false

                            Logger.d(
                                tag = "LoginScreen",
                                messageString = (errorMessage)
                            )

                        }

                        else -> {

                        }
                    }


                }



            ) {
                Text( text = "Login",)
            }

            if (isLoading) {
                Spacer(modifier = Modifier.height(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = 8.dp)
                )
            }
        }


        item {
            Row(
                modifier = Modifier
                    .padding(top = 32.dp)
            ) {
                Text(
                    text = "DON'T have an account?  ",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = LocalContentColor.current
                    ),
                )

                Text(
                    text = "Sign Up Instead",
                    modifier = Modifier.clickable {
                        navigateToSignUpScreen()
                    },
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
