package navigation

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.Navigator
import navigation.graphs.RootNavigationGraph
import viewmodel.HBAdminViewModel

@Composable
fun SetUpNavigation(
    navController: Navigator,
    hBAdminViewModel: HBAdminViewModel
) {

    RootNavigationGraph(
        navController = navController,
        hBAdminViewModel = hBAdminViewModel

    )
}