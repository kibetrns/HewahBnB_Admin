package navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import viewmodel.HBAdminViewModel

@Composable
fun RootNavigationGraph(
    navController: Navigator,
    hBAdminViewModel: HBAdminViewModel
) {
    val navRailItems = listOf("Dashboard", "Bookings", "Properties", "Settings")





    val selectedItem by remember { mutableStateOf(0) }

    NavHost(
        navigator = navController,
        initialRoute = Graph.AUTHENTICATION
    ) {

        authNavGraph(
            navController = navController,
            hBAdminViewModel = hBAdminViewModel
        )

        homeNavGraph(
            navRailItems = navRailItems,
            selectedItem = selectedItem,
            navController = navController,
            hBAdminViewModel = hBAdminViewModel
        )

        bookingsGraph(
            navRailItems = navRailItems,
            selectedItem = selectedItem,
            navController = navController,
            hBAdminViewModel = hBAdminViewModel
        )

        propertiesGraph(
            navRailItems = navRailItems,
            selectedItem = selectedItem,
            navController = navController,
            hBAdminViewModel = hBAdminViewModel
        )

        settingsGraph(
            navRailItems = navRailItems,
            selectedItem = selectedItem,
            navController = navController,
            hBAdminViewModel = hBAdminViewModel
        )

    }
}
object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val BOOKINGS = "bookings_graph"
    const val PROPERTIES = "properties_graph"
    const val SETTINGS = "settings_graph"
}