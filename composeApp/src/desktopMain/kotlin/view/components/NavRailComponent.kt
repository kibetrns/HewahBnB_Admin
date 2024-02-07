package view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun NavRailComponent(
    navRailItems: List<String>,
    selectedItem: Int,
    navigateToDashboardScreen: () -> Unit,
    navigateToBookingsScreen: () -> Unit,
    navigateToPropertiesScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit,
    modifier: Modifier
) {

    NavigationRail(
        modifier = modifier,
        header = {
            Image(
                painter = painterResource(resourcePath = "hewabnb_logo.png"),
                contentDescription = "HewahBnBlogo",
                modifier = Modifier.size(150.dp)
            )
            Text(text = "HewahBnB_Admin")
        },

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Button(
                onClick = {

                },
            ) {
                Row {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = null,
                    )

                    Text(
                        text = "Create",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            NavigationRailItem(
                label = { Text(text = navRailItems.component1()) },
                icon = { },
                selected = selectedItem == navRailItems.indexOf("Dashboard"),
                onClick = navigateToDashboardScreen,
                alwaysShowLabel = true
            )

            NavigationRailItem(
                label = { Text(text = navRailItems.component2()) },
                icon = { },
                selected = selectedItem == navRailItems.indexOf("Bookings"),
                onClick = navigateToBookingsScreen,
                alwaysShowLabel = true
            )

            NavigationRailItem(
                label = { Text(text = navRailItems.component3()) },
                icon = { },
                selected = selectedItem == navRailItems.indexOf("Properties"),
                onClick = navigateToPropertiesScreen,
                alwaysShowLabel = true
            )

            NavigationRailItem(
                label = { Text(text = navRailItems.component4()) },
                icon = { },
                selected = selectedItem == navRailItems.indexOf("Settings"),
                onClick = navigateToSettingsScreen,
                alwaysShowLabel = true
            )
        }
    }
}