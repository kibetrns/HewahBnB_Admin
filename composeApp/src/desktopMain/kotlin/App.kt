import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.KoinContext
import viewmodel.HBAdminViewModel

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    PreComposeApp {

        KoinContext {
            //    val createdUser =  rememberUpdatedState(hBAdminViewModel.createdUser.collectAsState().value)

            val scope = rememberCoroutineScope()
            val hBAdminViewModel = koinViewModel(HBAdminViewModel::class)

            MaterialTheme {
                var showContent by remember { mutableStateOf(false) }
                val greeting = remember { Greeting().greet() }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        showContent = !showContent

                        scope.launch {
                            val signUpResult = async {
                                hBAdminViewModel.loginUser()
                            }

                            signUpResult.await()
                        }

                    }) {
                        Text("Click me!")
                    }
                    AnimatedVisibility(showContent) {
                        Column(
                            Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(painterResource("compose-multiplatform.xml"), null)
                            Text("Compose: $greeting")
                        }
                    }
                }
            }
        }

    }
}