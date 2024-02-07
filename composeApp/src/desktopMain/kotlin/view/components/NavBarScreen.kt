package view.components


sealed class NavBarScreen(
    val route: String,
    val title: String,
) {
    object Dashboard : NavBarScreen(
        route = "dashboard",
        title = "Dashboard",
    )
    object Bookings :  NavBarScreen(
        route = "bookings",
        title = "Bookings",
    )
    object Properties : NavBarScreen(
        route = "properties",
        title = "Properties",
    )

    object Settings : NavBarScreen(
        route = "settings",
        title = "Settings",
    )

}
