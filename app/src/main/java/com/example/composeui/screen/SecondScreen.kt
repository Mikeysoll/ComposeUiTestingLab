package com.example.composeui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

data class TodoItem(
    val id: Int,
    val text: String,
    val done: Boolean = false
)

enum class Filter {
    ALL, ACTIVE
}

@Composable
fun SecondScreen(
    onGoFirst: () -> Unit
) {
    var input by remember { mutableStateOf("") }
    var items by remember { mutableStateOf(listOf<TodoItem>()) }
    var filter by remember { mutableStateOf(Filter.ALL) }
    var idCounter by remember { mutableStateOf(0) }

    val visibleItems = when (filter) {
        Filter.ALL -> items
        Filter.ACTIVE -> items.filter { !it.done }
    }

    val activeCount = items.count { !it.done }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
            .testTag("second_screen_root"),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "TODO Mini App",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.testTag("title")
        )

        Text(
            text = "Активных задач: $activeCount",
            modifier = Modifier.testTag("active_count")
        )

        // INPUT
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.testTag("input_row")
        ) {

            TextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier
                    .weight(1f)
                    .testTag("task_input"),
                placeholder = { Text("Новая задача") }
            )

            Button(
                onClick = {
                    if (input.isNotBlank()) {
                        items = items + TodoItem(
                            id = idCounter++,
                            text = input
                        )
                        input = ""
                    }
                },
                modifier = Modifier.testTag("add_button")
            ) {
                Text("+")
            }
        }

        // FILTERS
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.testTag("filters_row")
        ) {

            FilterChip(
                selected = filter == Filter.ALL,
                onClick = { filter = Filter.ALL },
                label = { Text("Все") },
                modifier = Modifier.testTag("filter_all")
            )

            FilterChip(
                selected = filter == Filter.ACTIVE,
                onClick = { filter = Filter.ACTIVE },
                label = { Text("Активные") },
                modifier = Modifier.testTag("filter_active")
            )
        }

        // LIST
        if (visibleItems.isEmpty()) {
            Text(
                "Нет задач 😴",
                modifier = Modifier.testTag("empty_state")
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .testTag("todo_list")
            ) {
                items(visibleItems, key = { it.id }) { item ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("todo_item")
                            .testTag("todo_item_${item.id}"),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column {

                            Text(
                                text = item.text,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.testTag("todo_text_${item.id}")
                            )

                            Text(
                                text = if (item.done) "DONE" else "ACTIVE",
                                style = MaterialTheme.typography.labelSmall,
                                color = if (item.done)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.error,
                                modifier = Modifier.testTag("todo_status_${item.id}")
                            )
                        }

                        Row {

                            Checkbox(
                                checked = item.done,
                                onCheckedChange = { checked ->
                                    items = items.map {
                                        if (it.id == item.id)
                                            it.copy(done = checked)
                                        else it
                                    }
                                },
                                modifier = Modifier.testTag("todo_checkbox_${item.id}")
                            )

                            TextButton(
                                onClick = {
                                    items = items.filter { it.id != item.id }
                                },
                                modifier = Modifier.testTag("todo_delete_${item.id}")
                            ) {
                                Text("Del")
                            }
                        }
                    }
                }
            }
        }

        Button(
            onClick = onGoFirst,
            modifier = Modifier.testTag("first_screen_button")
        ) {
            Text("Перейти на 1 экран")
        }
    }
}