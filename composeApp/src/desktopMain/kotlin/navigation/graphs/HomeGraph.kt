package navigation.graphs

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import co.touchlab.kermit.Logger
import moe.tlaster.precompose.navigation.BackStackEntry
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import view.MainScreen
import viewmodel.HBAdminViewModel

fun RouteBuilder.homeNavGraph(
    navRailItems: List<String>,
    selectedItem: Int,
    navController: Navigator,
    hBAdminViewModel: HBAdminViewModel
) {



    group(
        route = Graph.HOME,
        initialRoute = HomeScreen.Dashboard.route
    ) {

        scene(
            route = HomeScreen.Dashboard.route,
        ) { navBackStackEntry: BackStackEntry ->



            LaunchedEffect(key1 = true) {
                //hbViewModel.fetchSearchedHouses("TODO")
            }

            MainScreen(
                navRailItems = navRailItems,
                selectedItem = selectedItem,
                navController = navController,
                hBAdminViewModel = hBAdminViewModel,
                navigateToBookingsScreen = {
                    Logger.d(
                        tag = "HOME_GRAPH_MAINSCREEN",
                        messageString = ("Navigate to Bookings Clicked")
                    )

                    /*
                    navController.navigate(
                        route = Graph.BOOKINGS
                    )

                     */
                },
                navigateToPropertiesScreen = {
                    Logger.d(
                        tag = "HOME_GRAPH_MAINSCREEN",
                        messageString = ("Navigate to Properties Clicked")
                    )
                    /*

                    navController.navigate(
                        route = Graph.PROPERTIES
                    )

                     */
                }
            ) {
                Logger.d(
                    tag = "HOME_GRAPH_MAINSCREEN",
                    messageString = ("Navigate to Settings Clicked")
                )

                /*
                navController.navigate(
                    route = Graph.SETTINGS
                )

                 */
            }
        }


    }
}

sealed class HomeScreen(val route: String) {
    data object Dashboard: HomeScreen(route = "dashboard")

}