package navigation.graphs

import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import moe.tlaster.precompose.navigation.BackStackEntry
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import view.MainScreen
import viewmodel.HBAdminViewModel

fun RouteBuilder.propertiesGraph(
    navRailItems: List<String>,
    selectedItem: Int,
    navController: Navigator,
    hBAdminViewModel: HBAdminViewModel
) {

    group(
        route = Graph.PROPERTIES,
        initialRoute = PropertiesScreen.Properties.route
    ) {

        scene(
            route = PropertiesScreen.Properties.route,
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

sealed class PropertiesScreen(val route: String) {
    data object Properties: BookingsScreen(route = "properties")

}