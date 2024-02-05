package navigation.graphs

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import viewmodel.HBAdminViewModel

@Composable
fun RootNavigationGraph(
    navController: Navigator,
    hBAdminViewModel: HBAdminViewModel
) {

    NavHost(
        navigator = navController,
        initialRoute = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController, hBAdminViewModel = hBAdminViewModel)

        homeNavGraph(navController = navController, hBAdminViewModel = hBAdminViewModel)

    }
}
object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val PROFILE = "profile_graph"
}