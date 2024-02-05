package view.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.NavigationRail
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun NavRail() {
    NavigationRail(
        header = {
            Text(text = "LOGO") //TODO("Add a Logo)
            Text(text = "HewahBnB")
        }
    ) {


    }


}

@Preview()
@Composable
fun NavRailPreview() {
    NavRail()
}