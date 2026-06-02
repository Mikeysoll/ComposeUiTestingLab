package com.example.composeui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class FirstScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun start_state_should_show_guest() {
        composeTestRule.setContent {FirstScreen()}

        composeTestRule
            .onNodeWithText("Статус: Гость")
            .assertIsDisplayed()
    }

    @Test
    fun click_login_button_should_change_status() {
        composeTestRule.setContent {FirstScreen()}

        composeTestRule.onNodeWithText("Войти / Выйти")
            .performClick()
        composeTestRule.onNodeWithText("Статус: В системе")
            .assertTextEquals("Статус: В системе")
    }

    @Test
    fun counter_should_increment() {
        composeTestRule.setContent {FirstScreen()}

        composeTestRule
            .onNodeWithText("+")
            .performClick()

        composeTestRule
            .onNodeWithText("Счётчик: 1")
            .assertIsDisplayed()
    }

    @Test
    fun counter_should_decrement() {
        composeTestRule.setContent {FirstScreen()}

        composeTestRule
            .onNodeWithText("+")
            .performClick()

        composeTestRule
            .onNodeWithText("Счётчик: 1")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("-")
            .performClick()

        composeTestRule
            .onNodeWithText("Счётчик: 0")
            .assertIsDisplayed()
    }

    @Test
    fun error_should_show_when_counter_below_zero_blocked() {
        composeTestRule.setContent {FirstScreen()}

        composeTestRule
            .onNodeWithText("Счётчик: 0")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("-")
            .performClick()

        composeTestRule
            .onNodeWithText("Ошибка: нельзя уйти в минус")
            .assertIsDisplayed()
    }
}