package view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.Navigator
import view.components.NavRailComponent
import viewmodel.HBAdminViewModel

@Composable
fun MainScreen(
    navRailItems: List<String>,
    selectedItem: Int,
    navController: Navigator,
    hBAdminViewModel: HBAdminViewModel,
    navigateToBookingsScreen: () -> Unit,
    navigateToPropertiesScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit
) {

    var showDashboardScreen by remember { mutableStateOf(true) }

    var showBookingsScreen by remember { mutableStateOf(true) }

    var showPropertiesScreen by remember { mutableStateOf(true) }

    var showSettingsScreen by remember { mutableStateOf(true) }




    Scaffold {
        Row() {
            NavRailComponent(
                navRailItems = navRailItems,
                selectedItem = selectedItem,
                modifier = Modifier
                    .padding(16.dp),
                navigateToDashboardScreen = {
                    showDashboardScreen = true
                    showBookingsScreen = false
                    showPropertiesScreen = false
                    showSettingsScreen = false
                },
                navigateToBookingsScreen = {
                    showDashboardScreen = false
                    showBookingsScreen = true
                    showPropertiesScreen = false
                    showSettingsScreen = false

                },
                navigateToPropertiesScreen = {
                    showDashboardScreen = false
                    showBookingsScreen = false
                    showPropertiesScreen = true
                    showSettingsScreen = false

                },
                navigateToSettingsScreen = {
                    showDashboardScreen = false
                    showBookingsScreen = false
                    showPropertiesScreen = false
                    showSettingsScreen = true

                }
            )


            if (showDashboardScreen) {
                DashboardScreen()

            } else if (showBookingsScreen) {
                BookingsScreen(
                    navController = navController,
                    hBAdminViewModel = hBAdminViewModel
                )

            }else if (showPropertiesScreen) {
                PropertiesScreen(
                    navController = navController,
                    hBAdminViewModel = hBAdminViewModel,
                )

            } else if (showSettingsScreen) {
                SettingsScreen(
                    navController = navController,
                    hBAdminViewModel = hBAdminViewModel,
                )

            }

        }
    }
}

sealed class ScreenDisplay() {
   data class DASHBOARD(val title: String)
    data class  BOOKINGS(val title: String)
    data class PROPERTIES(val title: String)
    data class SETTINGS(val title: String)
}