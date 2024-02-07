package navigation.graphs

import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import view.LoginScreen
import view.SignUpScreen
import viewmodel.HBAdminViewModel

fun RouteBuilder.authNavGraph(
    navController: Navigator,
    hBAdminViewModel: HBAdminViewModel
) {

    group(
        route = Graph.AUTHENTICATION,
        initialRoute = AuthScreen.SignUP.route
    ) {
        scene(route = AuthScreen.SignUP.route) {
            SignUpScreen(
                hBAdminViewModel = hBAdminViewModel,
                navigateToHomeScreen = {
                    navController.navigate(
                        route = HomeScreen.Dashboard.route
                    )
                },
                navigateToLoginScreen = {
                    navController.navigate(
                        route = AuthScreen.Login.route,
                        /*
                        options = NavOptions(
                            launchSingleTop = true
                        )

                         */

                    )
                }
            )
        }

        scene(route = AuthScreen.Login.route) {
            LoginScreen(
                hBAdminViewModel = hBAdminViewModel,
                navigateToHomeScreen = {
                    //navController.popBackStack()
                    navController.navigate(
                        route = HomeScreen.Dashboard.route,
                        /*
                        options = NavOptions(
                            launchSingleTop = true
                        )

                         */
                    )
                },

                navigateToSignUpScreen = {
                    navController.navigate(
                        route = AuthScreen.SignUP.route,
                        /*
                        options = NavOptions(
                            popUpTo = PopUpTo(
                                route = AuthScreen.SignUP.route
                            )
                        )
                        */
                    )
                }
            )
        }

    }
}


sealed class AuthScreen(val route: String) {
    object Login: AuthScreen(route = "logIn")
    object SignUP : AuthScreen(route = "signUp")
}

