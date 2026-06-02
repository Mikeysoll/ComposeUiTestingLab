package com.example.composeui

import androidx.compose.runtime.*

@Composable
fun App() {

    val screen = remember {
        mutableStateOf<Screen>(Screen.First)
    }

    when (screen.value) {

        Screen.First -> FirstScreen(
            onGoSecond = { screen.value = Screen.Second }
        )

        Screen.Second -> SecondScreen(
            onBack = { screen.value = Screen.First }
        )
    }
}

sealed class Screen {
    object First : Screen()
    object Second : Screen()
}