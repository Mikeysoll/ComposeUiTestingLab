package com.example.composeui
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.statusBarsPadding

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}
@Composable
fun FirstScreen(onGoSecond: () -> Unit = {}) {

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
                if (counter > 0) counter-- else showError = true
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

@Composable
fun SecondScreen(onBack: () -> Unit = {}) {

    var text by remember { mutableStateOf("Hello") }
    var counter by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = spacedBy(12.dp)
    ) {

        Text(text = text)

        Button(onClick = { text = "Changed!" }) {
            Text("Change text")
        }

        Text("Counter: $counter")

        Button(onClick = { counter++ }) {
            Text("+")
        }

        Button(onClick = onBack) {
            Text("Назад")
        }
    }
}
