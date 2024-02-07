package view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.Navigator
import viewmodel.HBAdminViewModel

@Composable
fun BookingsScreen(
    navController: Navigator,
    hBAdminViewModel: HBAdminViewModel,
) {
    Text(text = "Bookings Screen")

}