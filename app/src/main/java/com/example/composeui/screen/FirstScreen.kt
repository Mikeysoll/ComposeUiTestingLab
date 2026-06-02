package com.example.composeui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FirstScreen(
    onGoSecond: () -> Unit
) {
    var isLoggedIn by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp),
        verticalArrangement = spacedBy(12.dp)
    ) {

        Text(
            text = if (isLoggedIn) "Статус: В системе" else "Статус: Гость"
        )

        Button(onClick = { isLoggedIn = !isLoggedIn }) {
            Text("Войти / Выйти")
        }

        Button(onClick = onGoSecond) {
            Text("Перейти на 2 экран")
        }

        Text("Счётчик: $counter")

        Row(horizontalArrangement = spacedBy(8.dp)) {

            Button(onClick = { counter++ }) {
                Text("+")
            }

            Button(onClick = {
                if (counter > 0) counter--
                else showError = true
            }) {
                Text("-")
            }
        }

        if (showError) {
            Text(
                text = "Ошибка: нельзя уйти в минус",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}