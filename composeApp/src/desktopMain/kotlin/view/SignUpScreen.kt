package view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import data.model.UserType
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import moe.tlaster.precompose.koin.koinViewModel
import viewmodel.HBAdminViewModel
import util.Result
import view.components.MessageCont
import view.components.MessageType
import util.*

//@ComposableViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    hBAdminViewModel: HBAdminViewModel,
    navigateToHomeScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit
) {

    var passwordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }


    val password by hBAdminViewModel.password.collectAsState()
    val email by hBAdminViewModel.email.collectAsState()
    val fullName by hBAdminViewModel.fullName.collectAsState()
    val mpesaNumber by hBAdminViewModel.mpesaNumber.collectAsState()
    val confirmedPassword by hBAdminViewModel.confirmedPassword.collectAsState()

    val errorMessage by hBAdminViewModel.errorMessage.collectAsState()


    val isUserCreated by rememberUpdatedState(hBAdminViewModel.isUserCreated.value)
    val scope = rememberCoroutineScope()
    val createdUser by rememberUpdatedState(hBAdminViewModel.createdUser.collectAsState().value)

    var isDialogVisible by remember { mutableStateOf(false) }

    var expanded by remember { mutableStateOf(false) }
    val angle: Float by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f
    )

    var role by remember{
        mutableStateOf(UserType.ADMINISTRATOR)
    }

    var isPasswordInvalid by remember { mutableStateOf(false) }

    var isFieldsEmpty by remember { mutableStateOf(false) }

    var isEmailValid by remember { mutableStateOf(true) }

    var isSubmitAttempted by remember { mutableStateOf(false) }

    LaunchedEffect(createdUser) {
        if (createdUser is Result.Error) {
            isDialogVisible = true
        }
    }

    if (isPasswordInvalid && isSubmitAttempted) {
        AlertDialog(
            onDismissRequest = {
                isPasswordInvalid = false
            },
            title = {
                Text(
                    text = "Password Validation Error",
                )
            },
            confirmButton = {
                Button(onClick = {
                    isPasswordInvalid = false
                }) {
                    Text("OK")
                }
            },
            text = {
                MessageCont(
                    messageType = MessageType.Error,
                    message = "Please ensure passwords match and are 8 characters long."
                )
            }
        )
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


    when (createdUser) {
        is Result.Error -> {
            if (isDialogVisible) {

                AlertDialog(
                    onDismissRequest = {
                        isDialogVisible = false
                        isLoading = false
                    },
                    title = {

                        if ((createdUser as Result.Error).statusCode == null) {
                            Text(
                                text = "Error: ${(createdUser as Result.Error).message}"
                            )
                        } else {
                            Text(
                                text = "Error code ${(createdUser as Result.Error).statusCode}: ${(createdUser as Result.Error).message}"
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
                            message = "Failed: ${hBAdminViewModel.errorMessage.value}"
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
                        message = "User Created Successfully"
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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        item() {
            Image(
                painter = painterResource(resourcePath = "hewabnb_logo.png"),
                contentDescription = "HewaBnB Logo",
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.headlineSmall.copy(
                    MaterialTheme.colorScheme.primary
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
        }


        item {
            Text(
                text = "Full Name",
                style = MaterialTheme.typography.titleLarge,
            )

            OutlinedTextField(
                value = fullName,
                onValueChange = { hBAdminViewModel.fullName.value = it },
                label = { Text(text = "Enter Fullname") },
                singleLine = true,
                placeholder = { Text(text = "Full Name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )
            Spacer(modifier = Modifier.height(4.dp))


            Text(
                text = "Mpesa Number",
                style = MaterialTheme.typography.titleLarge,
            )

            OutlinedTextField(
                value = mpesaNumber.toString(),
                onValueChange = { hBAdminViewModel.mpesaNumber.value = it.toLong() },
                label = { Text(text = "Enter Mpesa Number") },
                singleLine = true,
                placeholder = { Text(text = "Mpesa Number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Email",
                style = MaterialTheme.typography.titleLarge,
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    hBAdminViewModel.email.value = it

                                },
                label = { Text(text = "Enter Email Address") },
                singleLine = true,
                placeholder = { Text(text = "Email Address") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )
            Spacer(modifier = Modifier.height(4.dp))


            Text(
                text = "Password",
                style = MaterialTheme.typography.titleLarge,
            )

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
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Confirm Password",
                style = MaterialTheme.typography.titleLarge,
            )

            OutlinedTextField(
                value = confirmedPassword,
                onValueChange = {
                    hBAdminViewModel.confirmedPassword.value = it
                    isPasswordInvalid = !isPasswordValid(password, it)
                                },
                label = { Text(text = "Confirm Password") },
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


            Text(
                text = "Role",
                style = MaterialTheme.typography.titleLarge,
            )

            Row(
                modifier = Modifier
                    .height(60.dp)
                    .clickable { expanded = true }
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(start = 8.dp),
               verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = role.name,
                )
                IconButton(
                    onClick = { expanded = true },
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .rotate( degrees = angle)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                role = UserType.ADMINISTRATOR
                        },
                            text = {
                                Text(
                                    modifier = Modifier
                                        .padding(start = 16.dp),
                                    text = UserType.ADMINISTRATOR.name,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        )

                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                role = UserType.SUPER_ADMINISTRATOR
                        },
                            text = {
                                Text(
                                    modifier = Modifier
                                        .padding(start = 16.dp),
                                    text = UserType.SUPER_ADMINISTRATOR.name,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

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

                    if (fullName.isEmpty() || mpesaNumber.toString().isEmpty() || email.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty()) {
                        isFieldsEmpty = true
                        return@Button
                    }

                    if (!isPasswordValid(password, confirmedPassword)) {
                        return@Button
                    }

                    if (!isEmailValid(email = email)) {
                        return@Button
                    }

                    isLoading = true
                    isSubmitAttempted = false

                    scope.launch {

                        val signUpResult = async {
                            hBAdminViewModel.signUpUser()
                        }

                        signUpResult.await()
                    }

                    when (createdUser) {
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
                                tag = "SignUpScreen",
                                messageString = (errorMessage)
                            )
                        }
                        else -> {
                        }
                    }

                }

            ) {
                Text( text = "Create Account")
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
                    text = "Already have an account?  ",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = LocalContentColor.current
                    ),
                )

                Text(
                    text = "Login Instead",
                    modifier = Modifier.clickable {
                        navigateToLoginScreen()
                    },
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Preview()
@Composable
fun pSignUpScreen() {

    val hBAdminViewModel = koinViewModel(HBAdminViewModel::class)


    MaterialTheme {
        SignUpScreen(
            hBAdminViewModel = hBAdminViewModel,
            navigateToLoginScreen = {},
            navigateToHomeScreen = {}
        )
    }
}


