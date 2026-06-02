package com.example.composeui

import androidx.compose.runtime.*
import com.example.composeui.screen.FirstScreen
import com.example.composeui.screen.SecondScreen

sealed class Screen {
    object First : Screen()
    object Second : Screen()
}

@Composable
fun App() {
    var screen by remember { mutableStateOf<Screen>(Screen.First) }

    when (screen) {
        Screen.First -> FirstScreen(
            onGoSecond = { screen = Screen.Second }
        )

        Screen.Second -> SecondScreen(
            onGoFirst = { screen = Screen.First }
        )
    }
}