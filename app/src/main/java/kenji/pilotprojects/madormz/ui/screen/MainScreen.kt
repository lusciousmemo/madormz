package kenji.pilotprojects.madormz.ui.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MainScreen() {
    GreetingScreen(name = "test")
}


@Composable
fun GreetingScreen(name: String) {
    if (LocalInspectionMode.current) {
        Text("Hello preview user!")
    } else {
        Text("Hello $name!")
    }
}