package navigation.graphs

import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import moe.tlaster.precompose.navigation.BackStackEntry
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import view.HomeScreen
import viewmodel.HBAdminViewModel

fun RouteBuilder.homeNavGraph(
    navController: Navigator,
    hBAdminViewModel: HBAdminViewModel
) {

    group(
        route = Graph.HOME,
        initialRoute = HomeScreen.HomeDashboard.route
    ) {

        scene(
            route = HomeScreen.Home.route,
        ) { navBackStackEntry: BackStackEntry ->

            LaunchedEffect(key1 = true) {
                //hbViewModel.fetchSearchedHouses("TODO")
            }

            HomeScreen(
                hBAdminViewModel = hBAdminViewModel,
                navController = navController,
            )
        }

        scene(
            route = HomeScreen.HomeDashboard.route,
        ) { navBackStackEntry: BackStackEntry ->

           Text(text = "HOMESCREEN - DASHBOARD COMMING SOON")
        }
    }
}

sealed class HomeScreen(val route: String) {
    object Home: HomeScreen(route = "home")

    object HomeDashboard: HomeScreen(route = "home/dashboard")

}