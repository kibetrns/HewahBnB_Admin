package view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.Navigator
import viewmodel.HBAdminViewModel

@Composable
fun PropertiesScreen(
    navController: Navigator,
    hBAdminViewModel: HBAdminViewModel,
) {


    Text(text = "Properties Screen")


}