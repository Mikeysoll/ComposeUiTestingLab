package com.example.composeui

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import com.example.composeui.screen.SecondScreen
import org.jetbrains.annotations.TestOnly
import org.junit.Rule
import org.junit.Test

class SecondScreenTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun initialUiStateDisplayed() {
        rule.setContent { SecondScreen(onGoFirst = {}) }

        rule.onNodeWithTag("title")
            .assertIsDisplayed()
        rule.onNodeWithTag("active_count")
            .assertTextContains("Активных задач: 0")
    }

    @Test
    fun emptyStateShowsZeroTasks() {
        rule.setContent { SecondScreen(onGoFirst = {}) }

        rule.onNodeWithTag("active_count")
            .assertTextContains("Активных задач: 0")
        rule.onNodeWithTag("empty_state")
            .assertIsDisplayed()
    }

    @Test
    fun addFirstTask() {
        rule.setContent { SecondScreen(onGoFirst = {}) }

        val taskText = "Первая задача"

        rule.onNodeWithTag("task_input")
            .performTextInput(taskText)
        rule.onNodeWithTag("add_button")
            .performClick()

        rule.onNodeWithTag("todo_text_0")
            .assertTextEquals("Первая задача")
        rule.onNodeWithTag("active_count")
            .assertTextEquals("Активных задач: 1")
        rule.onNodeWithTag("todo_status_0")
            .assertTextContains("ACTIVE")
    }

    @Test
    fun deleteTaskFromList() {
        rule.setContent { SecondScreen(onGoFirst = {}) }

        val taskText = "Первая задача"

        rule.onNodeWithTag("task_input")
            .performTextInput(taskText)
        rule.onNodeWithTag("add_button")
            .performClick()

        rule.onNodeWithTag("active_count")
            .assertTextEquals("Активных задач: 1")

        rule.onNodeWithTag("todo_delete_0")
            .performClick()

        rule.onNodeWithTag("active_count")
            .assertTextEquals("Активных задач: 0")
        rule.onNodeWithTag("empty_state")
            .assertIsDisplayed()
    }

    @Test
    fun inputClearsAfterAdd() {
        rule.setContent { SecondScreen(onGoFirst = {}) }

        val taskText = "Первая задача"

        rule.onNodeWithTag("task_input")
            .performTextInput(taskText)
        rule.onNodeWithTag("add_button")
            .performClick()

        rule.onNodeWithTag("task_input")
            .assert(hasText(""))
    }

    @Test
    fun addMultipleTasks() {
        rule.setContent { SecondScreen(onGoFirst = {}) }

        val tasks = listOf("Первая задача", "Вторая задача", "Третья задача")

        for (task in tasks) {
            rule.onNodeWithTag("task_input")
                .performTextInput(task)
            rule.onNodeWithTag("add_button")
                .performClick()
        }

        rule.onNodeWithTag("active_count")
            .assertTextEquals("Активных задач: ${tasks.size}")
        rule.onAllNodesWithTag("todo_item")
            .assertCountEquals(3)
    }

    @Test
    fun markTaskAsDone() {
        rule.setContent { SecondScreen(onGoFirst = {}) }

        rule.onNodeWithTag("task_input")
            .performTextInput("Первая задача")
        rule.onNodeWithTag("add_button")
            .performClick()
        rule.onNodeWithTag("todo_checkbox_0")
            .performClick()

        rule.onNodeWithTag("todo_status_0")
            .assertTextContains("DONE")
    }

    @Test
    fun markTaskAsActiveAgain() {
        rule.setContent { SecondScreen(onGoFirst = {}) }

        rule.onNodeWithTag("task_input")
            .performTextInput("Первая задача")
        rule.onNodeWithTag("add_button")
            .performClick()
        rule.onNodeWithTag("todo_checkbox_0")
            .performClick()
        rule.onNodeWithTag("todo_checkbox_0")
            .performClick()

        rule.onNodeWithTag("todo_status_0")
            .assertTextContains("ACTIVE")
    }

    @Test
    fun activeTaskCounter() {
        rule.setContent { SecondScreen(onGoFirst = {}) }

        val tasks = listOf("Первая задача", "Вторая задача", "Третья задача")

        for (task in tasks) {
            rule.onNodeWithTag("task_input")
                .performTextInput(task)
            rule.onNodeWithTag("add_button")
                .performClick()
        }
        rule.onNodeWithTag("active_count")
            .assertTextEquals("Активных задач: ${tasks.size}")

        rule.onNodeWithTag("todo_checkbox_0")
            .performClick()

        rule.onNodeWithTag("active_count")
            .assertTextEquals("Активных задач: ${tasks.size - 1}")
    }

    @Test
    fun filterAll() {
        rule.setContent { SecondScreen(onGoFirst = {}) }

        val tasks = listOf(
            "Первая задача",
            "Вторая задача",
            "Третья задача",
            "Четвертая задача",
            "Пятая задача"
        )

        for (task in tasks) {
            rule.onNodeWithTag("task_input")
                .performTextInput(task)
            rule.onNodeWithTag("add_button")
                .performClick()
        }

        rule.onNodeWithTag("filter_all")
            .assertIsSelected()
        rule.onAllNodesWithTag("todo_item")
            .assertCountEquals(5)
    }

    @Test
    fun filterActive() {
        rule.setContent { SecondScreen(onGoFirst = {}) }

        val tasks = listOf(
            "Первая задача",
            "Вторая задача",
            "Третья задача",
            "Четвертая задача",
            "Пятая задача"
        )

        for (task in tasks) {
            rule.onNodeWithTag("task_input")
                .performTextInput(task)
            rule.onNodeWithTag("add_button")
                .performClick()
        }
        rule.onNodeWithTag("todo_checkbox_0")
            .performClick()
        rule.onNodeWithTag("todo_checkbox_1")
            .performClick()
        rule.onNodeWithTag("filter_active")
            .performClick()

        rule.onAllNodesWithTag("todo_item")
            .assertCountEquals(3)
    }

    @Test
    fun filterAllAgain() {
        rule.setContent { SecondScreen(onGoFirst = {}) }

        val tasks = listOf(
            "Первая задача",
            "Вторая задача",
            "Третья задача",
            "Четвертая задача",
            "Пятая задача"
        )

        for (task in tasks) {
            rule.onNodeWithTag("task_input")
                .performTextInput(task)
            rule.onNodeWithTag("add_button")
                .performClick()
        }
        rule.onNodeWithTag("todo_checkbox_0")
            .performClick()
        rule.onNodeWithTag("todo_checkbox_1")
            .performClick()
        rule.onNodeWithTag("filter_active")
            .performClick()

        rule.onAllNodesWithTag("todo_item")
            .assertCountEquals(3)

        rule.onNodeWithTag("filter_all")
            .performClick()

        rule.onAllNodesWithTag("todo_item")
            .assertCountEquals(5)
    }

    @Test
    fun removeTaskFromFilterView() {
        rule.setContent { SecondScreen(onGoFirst = {}) }

        val tasks = listOf(
            "Первая задача",
            "Вторая задача",
            "Третья задача",
            "Четвертая задача",
            "Пятая задача"
        )

        for (task in tasks) {
            rule.onNodeWithTag("task_input")
                .performTextInput(task)
            rule.onNodeWithTag("add_button")
                .performClick()
        }
        rule.onNodeWithTag("filter_active")
            .performClick()

        rule.onAllNodesWithTag("todo_item")
            .assertCountEquals(5)

        rule.onNodeWithTag("todo_checkbox_0")
            .performClick()

        rule.onAllNodesWithTag("todo_item")
            .assertCountEquals(4)
        rule.onNodeWithText("Первая задача")
            .assertDoesNotExist()
    }

    @Test
    fun updatesListCorrectly() {
        rule.setContent { SecondScreen(onGoFirst = {}) }

        val tasks = listOf(
            "Первая задача",
            "Вторая задача",
            "Третья задача",
            "Четвертая задача",
            "Пятая задача"
        )

        for (task in tasks) {
            rule.onNodeWithTag("task_input")
                .performTextInput(task)
            rule.onNodeWithTag("add_button")
                .performClick()
        }

        rule.onAllNodesWithTag("todo_item")
            .assertCountEquals(5)

        rule.onNodeWithTag("todo_delete_0")
            .performClick()

        rule.onAllNodesWithTag("todo_item")
            .assertCountEquals(4)
        rule.onNodeWithText("Первая задача")
            .assertDoesNotExist()
        rule.onNodeWithTag("todo_text_0")
            .assertDoesNotExist()
        rule.onNodeWithTag("todo_text_1")
            .assertTextEquals("Вторая задача")
        rule.onNodeWithTag("todo_text_2")
            .assertTextEquals("Третья задача")
        rule.onNodeWithTag("todo_text_3")
            .assertTextEquals("Четвертая задача")
        rule.onNodeWithTag("todo_text_4")
            .assertTextEquals("Пятая задача")
    }

    @Test
    fun checkEmptyStateWithFilter() {
        rule.setContent { SecondScreen(onGoFirst = {}) }

        val tasks = listOf(
            "Первая задача",
            "Вторая задача",
            "Третья задача",
        )

        for (task in tasks) {
            rule.onNodeWithTag("task_input")
                .performTextInput(task)
            rule.onNodeWithTag("add_button")
                .performClick()
        }

        rule.onNodeWithTag("filter_active")
            .performClick()

        for (i in 0..2) {
            rule.onNodeWithTag("todo_checkbox_$i")
                .performClick()
        }
        rule.onNodeWithTag("empty_state")
            .assertTextEquals("Нет задач 😴")
    }
}




































