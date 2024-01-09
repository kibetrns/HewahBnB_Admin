package view
/*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import viewmodel.HBAdminViewModel

//@ComposableViewModel
fun SignUpScreen(
    hBAdminViewModel: HBAdminViewModel,
    navigateToHomeScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit
) {

    var passwordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }


    val password by remember { hBAdminViewModel.password }
    val email by remember { hBAdminViewModel.email }
    val fullName by remember { hBAdminViewModel.fullName }
    val mpesaNumber by remember { hBAdminViewModel.mpesaNumber }
    val confirmedPassword by remember { hBAdminViewModel.confirmedPassword }

    val errorMessage by remember { hBAdminViewModel.errorMessage }


    val isUserCreated by rememberUpdatedState(hBAdminViewModel.isUserCreated.value)
    val scope = rememberCoroutineScope()
    val createdUser by rememberUpdatedState(hBAdminViewModel.createdUser.collectAsState().value)

    LaunchedEffect(createdUser) {
        when (createdUser) {
            is Result.Error -> {
                showToast(context, "Failed: ${hBAdminViewModel.errorMessage.value}")
            }
            else -> {}
        }
    }


    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()

    ) {

        item() {
            Image(
                painter = painterResource(id = R.drawable.hewabnb_logo),
                contentDescription = stringResource(id = R.string.hewabnbLogo),
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = stringResource(id = R.string.signUp),
                style = MaterialTheme.typography.headlineSmall.copy(
                    MaterialTheme.colorScheme.primary
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
        }


        item {
            Text(
                text = stringResource(id = R.string.fullName),
                style = MaterialTheme.typography.titleLarge,
            )

            OutlinedTextField(
                value = fullName,
                onValueChange = { hBAdminViewModel.fullName.value = it },
                label = { Text(stringResource(id = R.string.enter_fullName)) },
                singleLine = true,
                placeholder = { Text(stringResource(id = R.string.fullName)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(id = R.string.mpesaNumber),
                style = MaterialTheme.typography.titleLarge,
            )

            OutlinedTextField(
                value = mpesaNumber.toString(),
                onValueChange = { hBAdminViewModel.mpesaNumber.value = it.toLong() },
                label = { Text(stringResource(id = R.string.enter_mpesaNumber)) },
                singleLine = true,
                placeholder = { Text(stringResource(id = R.string.mpesaNumber)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(id = R.string.email),
                style = MaterialTheme.typography.titleLarge,
            )

            OutlinedTextField(
                value = email,
                onValueChange = { hBAdminViewModel.email.value = it},
                label = { Text(stringResource(id = R.string.enter_email_address)) },
                singleLine = true,
                placeholder = { Text(stringResource(id = R.string.email_address)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )
            Spacer(modifier = Modifier.height(4.dp))


            Text(
                text = stringResource(id = R.string.password),
                style = MaterialTheme.typography.titleLarge,
            )

            OutlinedTextField(
                value = password,
                onValueChange = { hBAdminViewModel.password.value = it },
                label = { Text(stringResource(id = R.string.enter_password)) },
                singleLine = true,
                placeholder = { Text(stringResource(id = R.string.password)) },
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
                text = stringResource(id = R.string.confirmPassword),
                style = MaterialTheme.typography.titleLarge,
            )

            OutlinedTextField(
                value = confirmedPassword,
                onValueChange = { hBAdminViewModel.confirmedPassword.value = it },
                label = { Text(stringResource(id = R.string.enter_password)) },
                singleLine = true,
                placeholder = { Text(stringResource(id = R.string.password)) },
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
                    isLoading = true

                    scope.launch {

                        val signUpResult = async {
                            hBAdminViewModel.signUpUser()
                        }

                        signUpResult.await()
                    }

                    when (createdUser) {
                        is Result.Success -> {
                            isLoading = false
                            showToast(context, "Success: Account Created")
                            navigateToHomeScreen()
                        }

                        is Result.Loading -> {
                            isLoading = true
                        }

                        is Result.Error -> {
                            showToast(context, errorMessage)
                            Log.d("SignUpScreen", errorMessage)
                            isLoading = false
                        }

                        else -> {

                        }
                    }
                }

            ) {
                Text( text = stringResource(id = R.string.createAccount),)
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
                    text = stringResource(id = R.string.login_instead),
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

//@Preview(name = "LoginScreenPreview", showBackground = true, showSystemUi = true)
//@Composable
//fun pSignUpScreen() {
//
//    val HBAdminViewModel = hiltViewModel<HBAdminViewModel>()
//
//
//    HewahBnBTheme {
//        SignUpScreen(
//            HBAdminViewModel = HBAdminViewModel,
//            navController = ,
//            navigateToHomeScreen = {}
//        ) {}
//    }
//}
*/

