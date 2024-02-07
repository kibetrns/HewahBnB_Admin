package navigation.graphs

import androidx.compose.runtime.LaunchedEffect
import moe.tlaster.precompose.navigation.BackStackEntry
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import view.MainScreen
import viewmodel.HBAdminViewModel

fun RouteBuilder.settingsGraph(
    navRailItems: List<String>,
    selectedItem: Int,
    navController: Navigator,
    hBAdminViewModel: HBAdminViewModel
) {

    group(
        route = Graph.SETTINGS,
        initialRoute = SettingsScreen.Settings.route
    ) {

        scene(
            route = SettingsScreen.Settings.route,
        ) { navBackStackEntry: BackStackEntry ->

            LaunchedEffect(key1 = true) {
                //hbViewModel.fetchSearchedHouses("TODO")
            }

            /*

            MainScreen(
                navRailItems = navRailItems,
                selectedItem = selectedItem,
                navController = navController,
                hBAdminViewModel = hBAdminViewModel,
                navigateToBookingsScreen = {
                    navController.navigate(
                        route = Graph.BOOKINGS
                    )
                },
                navigateToPropertiesScreen = {
                    navController.navigate(
                        route = Graph.PROPERTIES
                    )
                },
                navigateToSettingsScreen = {
                    navController.navigate(
                        route = Graph.SETTINGS
                    )
                }
            )

             */
        }

    }
}

sealed class SettingsScreen(val route: String) {
    data object Settings: BookingsScreen(route = "settings")

}