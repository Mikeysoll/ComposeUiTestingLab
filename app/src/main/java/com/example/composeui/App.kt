package com.example.composeui

import androidx.compose.runtime.*

@Composable
fun App() {
    var screen by remember { mutableStateOf(Screen.First) }

    when (screen) {

        Screen.First -> FirstScreen(
            onGoSecond = { screen = Screen.Second }
        )

        Screen.Second -> SecondScreen(
            onBack = { screen = Screen.First }
        )
    }
}

sealed class Screen {
    object First : Screen()
    object Second : Screen()
}