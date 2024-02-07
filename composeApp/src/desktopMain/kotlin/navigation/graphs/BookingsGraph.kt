package navigation.graphs

import androidx.compose.runtime.LaunchedEffect
import moe.tlaster.precompose.navigation.BackStackEntry
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import view.MainScreen
import viewmodel.HBAdminViewModel

fun RouteBuilder.bookingsGraph(
    navRailItems: List<String>,
    selectedItem: Int,
    navController: Navigator,
    hBAdminViewModel: HBAdminViewModel
) {

    group(
        route = Graph.BOOKINGS,
        initialRoute = BookingsScreen.Bookings.route
    ) {

        scene(
            route = BookingsScreen.Bookings.route,
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

sealed class BookingsScreen(val route: String) {
    data object Bookings: BookingsScreen(route = "bookings")

}