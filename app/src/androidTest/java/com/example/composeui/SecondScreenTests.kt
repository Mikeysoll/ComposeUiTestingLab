package com.example.composeui

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.composeui.screen.SecondScreen
import org.jetbrains.annotations.TestOnly
import org.junit.Rule
import org.junit.Test

class SecondScreenTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun should_display_initial_ui_state() {
        rule.setContent { SecondScreen(onGoFirst = {}) }

        rule.onNodeWithTag("title")
            .assertIsDisplayed()
        rule.onNodeWithTag("active_count")
            .assertTextContains("Активных задач: 0")
    }

    @Test
    fun empty_number_of_tasks() {
        rule.setContent { SecondScreen(onGoFirst = {}) }

        rule.onNodeWithTag("active_count")
            .assertTextContains("Активных задач: 0")
        rule.onNodeWithTag("empty_state")
            .assertIsDisplayed()
    }

    @Test
    fun add_first_task() {
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
    }

    @Test
    fun delete_task_from_list() {
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
    fun empty_field_after_adding_task(){
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
    fun adding_multiple_tasks(){
        rule.setContent { SecondScreen(onGoFirst = {}) }

        val tasks = listOf("Первая задача", "Вторая задача", "Третья задача")

        rule.onNodeWithTag("task_input")
            .performTextInput(tasks[0])
        rule.onNodeWithTag("add_button")
            .performClick()
        rule.onNodeWithTag("task_input")
            .performTextInput(tasks[1])
        rule.onNodeWithTag("add_button")
            .performClick()
        rule.onNodeWithTag("task_input")
            .performTextInput(tasks[2])
        rule.onNodeWithTag("add_button")
            .performClick()

        rule.onNodeWithTag("active_count")
            .assertTextEquals("Активных задач: ${tasks.size}")
        rule.onAllNodesWithTag("todo_item")
            .assertCountEquals(3)

        Thread.sleep(5000)
    }
}


















